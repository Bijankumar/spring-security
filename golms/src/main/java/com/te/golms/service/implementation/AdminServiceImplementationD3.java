package com.te.golms.service.implementation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.te.golms.entity.Batch;
import com.te.golms.entity.Employee;
import com.te.golms.entity.GoLmsUser;
import com.te.golms.entity.Mentor;
import com.te.golms.entity.MockDetails;
import com.te.golms.entity.Request;
import com.te.golms.entity.Technology;
import com.te.golms.entity.model.AddEmployeeToBatchModel;
import com.te.golms.entity.model.BatchModel;
import com.te.golms.entity.model.MentorModel;
import com.te.golms.entity.model.RejectRegisterRequestModel;
import com.te.golms.entity.model.TechnologyModel;
import com.te.golms.enums.EmployeeStatus;
import com.te.golms.repository.AdminRepository;
import com.te.golms.repository.BatchRepository;
import com.te.golms.repository.EmployeeRepository;
import com.te.golms.repository.GoLmsUserRepository;
import com.te.golms.repository.MentorRepository;
import com.te.golms.repository.RequestListRepository;
import com.te.golms.repository.TechnologyRepository;
import com.te.golms.response.BatchResponseModel;
import com.te.golms.service.AdminService;
import com.te.golms.service.EmailService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImplementationD3 implements AdminService {

	private final AdminRepository adminRepository;
	private final EmployeeRepository employeeRepository;
	private final BatchRepository batchRepository;
	private final MentorRepository mentorRepository;
	private final RequestListRepository requestListRepository;
	private final TechnologyRepository technologyRepository;
	private final GoLmsUserRepository goLmsUserRepository;
	private final EmailService emailService;
	private final PasswordEncoder passwordEncoder;
	private final ModelMapper modelMapper;

	@Override
	public List<BatchResponseModel> getBatcheList() {
		List<BatchResponseModel> batchModels = new ArrayList<>();
		batchRepository.findAll().stream()
				.forEach(batch -> batchModels.add(modelMapper.map(batch, BatchResponseModel.class)));
		return batchModels;
	}

	@Override
	public BatchModel saveBatch(BatchModel batchModel) {
		Batch batch = new Batch();
		Set<Technology> technologies = new HashSet<>();
		Optional<Mentor> op = mentorRepository.findByMentorId(batchModel.getMentorId());
		for (TechnologyModel technologyModel : batchModel.getTechnologies()) {
			Optional<Technology> technologyModelOp = technologyRepository
					.findByTechnologyName(technologyModel.getTechnologyName());
			if (technologyModelOp.isPresent()) {
				technologies.add(technologyModelOp.get());
			} else {
				return null;
			}
		}
		if (op.isPresent()) {
			BeanUtils.copyProperties(batchModel, batch);
			Mentor mentor = op.get();
			batch.setBatchMentor(mentor);
			batch.setTechnologies(technologies);
		} else {
			return null;
		}
		BeanUtils.copyProperties(batchRepository.save(batch), batchModel);
		return batchModel;
	}

	@Override
	public BatchModel updateBatch(BatchModel batchModel) {
		Batch batch = new Batch();
		Optional<Batch> batchOp = batchRepository.findByBatchName(batchModel.getBatchName());
		Optional<Mentor> mentorOp = mentorRepository.findByMentorId(batchModel.getMentorId());
		if (batchOp.isPresent()) {
			for (TechnologyModel technologyModel : batchModel.getTechnologies()) {
				Optional<Technology> technologyModelOp = technologyRepository
						.findByTechnologyName(technologyModel.getTechnologyName());
				if (technologyModelOp.isPresent()) {
					batchOp.get().getTechnologies().add(technologyModelOp.get());
				}
			}
			BeanUtils.copyProperties(batchModel, batch);
			batch.setBatchId(batchOp.get().getBatchId());
			batch.setTechnologies(batchOp.get().getTechnologies());
			if (mentorOp.isPresent()) {
				batch.setBatchMentor(mentorOp.get());
			}
			BeanUtils.copyProperties(batchRepository.save(batch), batchModel);
			return batchModel;
		}
		return null;
	}

	@Override
	public Boolean addEmployeeToBatchModel(AddEmployeeToBatchModel addEmployeeToBatchModel) {
		Optional<Employee> opEmp = employeeRepository.findByEmpId(addEmployeeToBatchModel.getEmpId());
		Optional<Batch> opBatch = batchRepository.findByBatchName(addEmployeeToBatchModel.getBatchName());
		Optional<Request> opRequest = requestListRepository.findById(addEmployeeToBatchModel.getRequestId());
		Optional<GoLmsUser> opGoLms = goLmsUserRepository.findByUsername(addEmployeeToBatchModel.getEmpId());
		if (opEmp.isPresent() && opBatch.isPresent() && opRequest.isPresent() && opGoLms.isPresent()) {
			String tempPassword = UUID.randomUUID() + "";
			Employee employee = opEmp.get();
			Batch batch = opBatch.get();
			employee.setInBatch(batch);
			employee.setStatus(EmployeeStatus.ACTIVE);
			GoLmsUser goLmsUser = opGoLms.get();
			goLmsUser.setPassword(passwordEncoder.encode(tempPassword));
			goLmsUserRepository.save(goLmsUser);
			employeeRepository.save(employee);
			Request request = opRequest.get();
			requestListRepository.delete(request);
			String toEmail = employee.getEmail();
			String emailContent = "<h1>Hi " + employee.getEmpName() + ",</h1>"
					+ "<h3>Thank you for registering to the GoLms application!</h3>"
					+ "<h3>Your account registration is approved by the admin, and your temporary password is "
					+ tempPassword + " </h3>" + "\n\n" + "<h1>Thanks and Regards,</h1>" + "<h3>Technoelevate Team</h3>";
			String emailSubject = "Your registration is approved!";
			List<String> toEmails = new ArrayList<>();
			toEmails.add(toEmail);
			emailService.sendEmail(toEmails, emailSubject, emailContent);
			return true;
		}
		return false;
	}

	@Override
	public List<Request> getRequestList() {
		List<Request> allRequests = adminRepository.findAllRequests();
		for (Request req : allRequests) {
			for (MockDetails mockDetails : req.getEmployee().getMockDetails()) {
				mockDetails.setEmployee(null);
			}
		}
		return allRequests;
	}

	@Override
	public Boolean rejectRequest(RejectRegisterRequestModel rejectRequest) {
		Optional<Request> op = requestListRepository.findById(rejectRequest.getRequestId());
		if (op.isPresent()) {
			Request request = op.get();
			request.setIsRejected(true);
			request.setRejectionReason(rejectRequest.getRejectReason());
			requestListRepository.save(request);
			return true;
		}
		return false;
	}

	@Override
	public MentorModel saveMentor(MentorModel mentorModel) {
		Mentor mentor = modelMapper.map(mentorModel, Mentor.class);
		if (adminRepository.findByAdminId(mentor.getMentorId()).isPresent()
				|| employeeRepository.findByEmpId(mentor.getMentorId()).isPresent()
				|| mentorRepository.findByMentorId(mentor.getMentorId()).isPresent()) {
			return null;
		}
		if (adminRepository.findByEmail(mentor.getEmail()).isPresent()
				|| employeeRepository.findByEmail(mentor.getEmail()).isPresent()
				|| mentorRepository.findByEmail(mentor.getEmail()).isPresent()) {
			return null;
		}
		Set<Technology> technologies = new HashSet<>();
		for (TechnologyModel technologyModel : mentorModel.getTechnologies()) {
			Optional<Technology> technologyModelOp = technologyRepository
					.findByTechnologyName(technologyModel.getTechnologyName());
			if (technologyModelOp.isPresent()) {
				technologies.add(technologyModelOp.get());
			} else {
				return null;
			}
		}
		mentor.setTechnologies(technologies);
		Mentor savedMentor = mentorRepository.save(mentor);

		Set<String> roles = new HashSet<>();
		roles.add("ROLE_MENTOR");
		String tempPassword = passwordEncoder.encode("qwerty");
		GoLmsUser goLmsUser = new GoLmsUser();
		goLmsUser.setUsername(savedMentor.getMentorId());
		goLmsUser.setPassword(tempPassword);
		goLmsUser.setRoles(roles);
		goLmsUserRepository.save(goLmsUser);

		String toEmail = mentor.getEmail();
		String mentorName = mentor.getMentorName();
		String emailContent = "<h1>Hi " + mentorName + ",</h1>"
				+ "<h3>You have been registered by the admin in the GoLms application as a mentor!</h3>"
				+ "<h3>Your temporary password is " + tempPassword + " </h3>" + "\n\n" + "<h1>Thanks and Regards,</h1>"
				+ "<h3>Technoelevate Team</h3>";
		String emailSubject = "You are registered for GoLms!";
		List<String> toEmails = new ArrayList<>();
		toEmails.add(toEmail);
		emailService.sendEmail(toEmails, emailSubject, emailContent);
		BeanUtils.copyProperties(savedMentor, mentorModel);
		return mentorModel;
	}

	@Override
	public List<MentorModel> getMentorList() {
		List<MentorModel> mentorModels = new ArrayList<>();
		mentorRepository.findAll().stream()
				.forEach(mentor -> mentorModels.add(modelMapper.map(mentor, MentorModel.class)));
		return mentorModels;
	}

	@Override
	public Boolean deleteMentor(String mentorId) {
		Optional<Mentor> op = mentorRepository.findByMentorId(mentorId);
		if (op.isPresent()) {
			Mentor mentor = op.get();
			Optional<Batch> findByBatchName = batchRepository.findByBatchName(mentor.getTraningBatche().getBatchName());
			if (findByBatchName.isPresent()) {
				Batch batch = findByBatchName.get();
				batch.setBatchMentor(null);
				batchRepository.save(batch);
			}
			Optional<GoLmsUser> findByUsername = goLmsUserRepository.findByUsername(mentorId);
			if (findByUsername.isPresent()) {
				GoLmsUser goLmsUser = findByUsername.get();
				goLmsUserRepository.delete(goLmsUser);
			}
			mentor.setTechnologies(null);
			mentorRepository.save(mentor);
			mentorRepository.delete(op.get());
			return true;
		}
		return false;
	}

	@Override
	public Boolean deleteBatch(String batchName) {
		Optional<Batch> op = batchRepository.findByBatchName(batchName);
		if (op.isPresent()) {
			Batch batch = op.get();
			batch.setBatchMentor(null);
			batch.setTechnologies(null);
			batchRepository.save(batch);
			batchRepository.delete(batch);
			return true;
		}
		return false;
	}

}
