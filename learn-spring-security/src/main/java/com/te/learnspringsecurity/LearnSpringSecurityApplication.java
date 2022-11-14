package com.te.learnspringsecurity;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.te.learnspringsecurity.entity.Admin;
import com.te.learnspringsecurity.entity.AppUser;
import com.te.learnspringsecurity.entity.Employee;
import com.te.learnspringsecurity.entity.Mentor;
import com.te.learnspringsecurity.entity.Role;
import com.te.learnspringsecurity.repository.AdminRepository;
import com.te.learnspringsecurity.repository.AppUserRepository;
import com.te.learnspringsecurity.repository.EmployeeRepository;
import com.te.learnspringsecurity.repository.MentorRepository;
import com.te.learnspringsecurity.repository.RoleRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class LearnSpringSecurityApplication {

	private final AdminRepository adminRepository;
	private final MentorRepository mentorRepository;
	private final EmployeeRepository employeeRepository;
	private final AppUserRepository appUserRepository;
	private final RoleRepository roleRepository;

	private final PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		log.error("Application is going to start.");
		SpringApplication.run(LearnSpringSecurityApplication.class, args);
	}

	@Bean
	public CommandLineRunner saveInDb() {
		return a -> {
			log.error("CommandLineRunner getting executed.");
			Optional<Role> findByRoleName = roleRepository.findByRoleName("ROLE_ADMIN");
			if (!findByRoleName.isPresent()) {
				log.error("Data not present in database, so saving data in database.");
				Role adminRole = new Role();
				adminRole.setRoleName("ROLE_ADMIN");
				Role mentorRole = new Role();
				mentorRole.setRoleName("ROLE_MENTOR");
				Role employeeRole = new Role();
				employeeRole.setRoleName("ROLE_EMPLOYEE");
				roleRepository.save(adminRole);
				roleRepository.save(mentorRole);
				roleRepository.save(employeeRole);

				Admin admin = new Admin();
				admin.setAdminId("ADMIN01");
				admin.setAdminName("Admin");
				admin.setAdminEmail("admin@admin.com");
				adminRepository.save(admin);
				AppUser appUser = new AppUser();
				appUser.setUsername(admin.getAdminId());
				appUser.setPassword(passwordEncoder.encode("qwerty"));
				appUser.setRoles(Arrays.asList(adminRole));
				appUserRepository.save(appUser);

				Mentor mentor = new Mentor();
				mentor.setMentorId("MENTOR01");
				mentor.setMentorName("Mentor");
				mentor.setMentorEmail("mentor@mentor.com");
				mentorRepository.save(mentor);
				AppUser appUser2 = new AppUser();
				appUser2.setUsername(mentor.getMentorId());
				appUser2.setPassword(passwordEncoder.encode("qwerty"));
				appUser2.setRoles(Arrays.asList(mentorRole));
				appUserRepository.save(appUser2);

				Employee employee = new Employee();
				employee.setEmployeeId("EMPLOYEE01");
				employee.setEmployeeName("Employee");
				employee.setEmployeeEmail("employee@employee.com");
				employeeRepository.save(employee);
				AppUser appUser3 = new AppUser();
				appUser3.setUsername(employee.getEmployeeId());
				appUser3.setPassword(passwordEncoder.encode("qwerty"));
				appUser3.setRoles(Arrays.asList(employeeRole));
				appUserRepository.save(appUser3);
			}
		};
	}

}
