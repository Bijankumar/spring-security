package com.te.golms.service.implementation;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.te.golms.entity.Admin;
import com.te.golms.entity.Employee;
import com.te.golms.entity.GoLmsUser;
import com.te.golms.entity.Mentor;
import com.te.golms.entity.Request;
import com.te.golms.entity.model.EmployeeModel;
import com.te.golms.entity.model.PasswordResetModel;
import com.te.golms.enums.EmployeeStatus;
import com.te.golms.repository.AdminRepository;
import com.te.golms.repository.EmployeeRepository;
import com.te.golms.repository.GoLmsUserRepository;
import com.te.golms.repository.MentorRepository;
import com.te.golms.repository.RequestListRepository;
import com.te.golms.service.GoLmsUserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GoLmsServiceImplementation implements GoLmsUserService, UserDetailsService {

	private final GoLmsUserRepository goLmsUserRepository;
	private final EmployeeRepository employeeRepository;
	private final AdminRepository adminRepository;
	private final MentorRepository mentorRepository;
	private final RequestListRepository requestListRepository;
	private final PasswordEncoder passwordEncoder;
	private final ModelMapper modelMapper;

	@Override
	public Optional<Admin> findByAdminId(String adminId) {
		return adminRepository.findByAdminId(adminId);
	}

	@Override
	public Optional<Mentor> findByMentorId(String mentorId) {
		return mentorRepository.findByMentorId(mentorId);
	}

	@Override
	public Optional<Employee> findByEmpId(String empId) {
		return employeeRepository.findByEmpId(empId);
	}

	@Override
	public EmployeeModel saveEmployee(EmployeeModel employeeModel) {
		if (adminRepository.findByAdminId(employeeModel.getEmpId()).isPresent()
				|| employeeRepository.findByEmpId(employeeModel.getEmpId()).isPresent()
				|| mentorRepository.findByMentorId(employeeModel.getEmpId()).isPresent()) {
			return null;
		}
		if (adminRepository.findByEmail(employeeModel.getEmail()).isPresent()
				|| employeeRepository.findByEmail(employeeModel.getEmail()).isPresent()
				|| mentorRepository.findByEmail(employeeModel.getEmail()).isPresent()) {
			return null;
		}
		Set<String> roles = new HashSet<>();
		roles.add("ROLE_EMPLOYEE");

		Employee employee = new Employee();
		modelMapper.map(employeeModel, employee);

		GoLmsUser goLmsUser = new GoLmsUser();
		goLmsUser.setUsername(employee.getEmpId());
		goLmsUser.setPassword(passwordEncoder.encode(UUID.randomUUID() + ""));
		goLmsUser.setRoles(roles);

		Request request = new Request();
		request.setEmployee(employee);

		employeeRepository.save(employee);
		goLmsUserRepository.save(goLmsUser);
		requestListRepository.save(request);
		return employeeModel;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<GoLmsUser> findByUsername = goLmsUserRepository.findByUsername(username);
		if (findByUsername.isPresent()) {
			GoLmsUser goLmsUser = findByUsername.get();
			Set<GrantedAuthority> authorities = goLmsUser.getRoles().stream().map(SimpleGrantedAuthority::new)
					.collect(Collectors.toSet());
			return new User(goLmsUser.getUsername(), goLmsUser.getPassword(), authorities);
		}
		return null;
	}

	@Override
	public boolean passwordReset(PasswordResetModel reset) {
		Optional<GoLmsUser> findByUsername = goLmsUserRepository.findByUsername(reset.getEmpId());
		if (findByUsername.isPresent()) {
			GoLmsUser goLmsUser = findByUsername.get();
			goLmsUser.setPassword(passwordEncoder.encode(reset.getNewPassword()));
			goLmsUser.setPasswordReset(true);
			goLmsUserRepository.save(goLmsUser);
			return true;
		}
		return false;
	}

	@Override
	public Boolean firstTimeLoggedIn(String id) {
		Optional<Employee> opEmp = employeeRepository.findByEmpId(id);
		if (opEmp.isPresent() && opEmp.get().getLastLogin() == null) {
			Employee employee = opEmp.get();
			employee.setStatus(EmployeeStatus.ACTIVE);
			employeeRepository.save(employee);
			return true;
		}
		Optional<Mentor> opMentor = mentorRepository.findByMentorId(id);
		if (opMentor.isPresent() && opMentor.get().getLastLogin() == null) {
			Mentor mentor = opMentor.get();
			mentorRepository.save(mentor);
			return true;
		}
		return false;
	}

	@Override
	public Boolean setLastLogin(String empId) {
		Optional<Mentor> op1 = findByMentorId(empId);
		if (op1.isPresent()) {
			Mentor mentor = op1.get();
			mentor.setLastLogin(LocalDateTime.now());
			mentorRepository.save(mentor);
			return true;
		}
		Optional<Employee> op2 = findByEmpId(empId);
		if (op2.isPresent()) {
			Employee employee = op2.get();
			employee.setLastLogin(LocalDateTime.now());
			employeeRepository.save(employee);
			return true;
		}
		return false;
	}

	@Override
	public boolean checkPasswordReset(String empId) {
		Optional<GoLmsUser> findByUsername = goLmsUserRepository.findByUsername(empId);
		if (findByUsername.isPresent()) {
			return findByUsername.get().isPasswordReset() && findByUsername.get().isAccountActive();
		}
		return false;
	}

}
