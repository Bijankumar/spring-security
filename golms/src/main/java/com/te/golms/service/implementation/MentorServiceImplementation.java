package com.te.golms.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.te.golms.entity.Batch;
import com.te.golms.entity.Employee;
import com.te.golms.entity.Mentor;
import com.te.golms.entity.MockDetails;
import com.te.golms.entity.MockDetailsModel;
import com.te.golms.entity.Technology;
import com.te.golms.entity.model.BatchModel;
import com.te.golms.entity.model.EmpStatusModel;
import com.te.golms.entity.model.MockModel;
import com.te.golms.enums.EmployeeStatus;
import com.te.golms.repository.EmployeeRepository;
import com.te.golms.repository.MentorRepository;
import com.te.golms.repository.TechnologyRepository;
import com.te.golms.service.EmailService;
import com.te.golms.service.MentorService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MentorServiceImplementation implements MentorService {

	private final MentorRepository mentorRepository;
	private final EmployeeRepository employeeRepository;
	private final TechnologyRepository technologyRepository;
	private final EmailService emailService;
	private final ModelMapper modelMapper;

	@Override
	public BatchModel getBatchData(String mentorId) {
		Optional<Mentor> op = mentorRepository.findByMentorId(mentorId);
		if (op.isPresent()) {
			Batch traningBatche = op.get().getTraningBatche();
			if (traningBatche != null) {
				for (Employee emp : traningBatche.getEmployees()) {
					emp.setInBatch(null);
				}
				BatchModel batchModel = new BatchModel();
				BeanUtils.copyProperties(traningBatche, batchModel);
				return batchModel;
			} else {
				return null;
			}
		}
		return null;
	}

	@Override
	public Boolean addEmpMockData(String empId, MockDetailsModel mockDetailsModel) {
		MockDetails mockDetails = modelMapper.map(mockDetailsModel, MockDetails.class);
		Optional<Employee> op = employeeRepository.findByEmpId(empId);
		if (op.isPresent()) {
			Employee employee = op.get();
			employee.getMockDetails().add(mockDetails);
			mockDetails.setEmployee(employee);
			employeeRepository.save(employee);
			return true;
		}
		return false;
	}

	@Override
	public Boolean changeEmployeeStatus(EmpStatusModel empStatusModel) {
		Optional<Employee> op = employeeRepository.findByEmpId(empStatusModel.getEmpId());
		if (op.isPresent()) {
			Employee employee = op.get();
			if (empStatusModel.getEmpStatus().equals(EmployeeStatus.ACTIVE)) {
				employee.setStatus(EmployeeStatus.ACTIVE);
			} else if (empStatusModel.getEmpStatus().equals(EmployeeStatus.ABSCONDED)) {
				employee.setStatus(EmployeeStatus.ABSCONDED);
			} else if (empStatusModel.getEmpStatus().equals(EmployeeStatus.TERMINATED)) {
				employee.setStatus(EmployeeStatus.TERMINATED);
			}
			employeeRepository.save(employee);
			return true;
		}
		return false;
	}

	@Override
	public Boolean createNewMock(String mentorId, MockModel mockModel) {
		Optional<Mentor> opMentor = mentorRepository.findByMentorId(mentorId);
		Optional<Technology> opTech = technologyRepository
				.findByTechnologyName(mockModel.getTechnology().getTechnologyName());
		if (opMentor.isPresent() && opTech.isPresent()) {
			Mentor mentor = opMentor.get();
			Technology technology = opTech.get();
			List<Employee> employees = mentor.getTraningBatche().getEmployees();
			String emailContent = "<h1>Hi dear,</h1>" + "<h3>You have an upcoming mock '" + mockModel.getMockOn()
					+ "', technology is " + technology.getTechnologyName() + ", and mock date and time is "
					+ mockModel.getMockTime() + "!</h3>" + "<h1>Thanks and Regards,</h1>"
					+ "<h3>Technoelevate Team</h3>";
			String emailSubject = "Mock comming soon!";
			List<String> toEmails = new ArrayList<>();
			for (Employee employee : employees) {
				toEmails.add(employee.getEmail());
			}
			emailService.sendEmail(toEmails, emailSubject, emailContent);
			return true;
		}
		return false;
	}
}
