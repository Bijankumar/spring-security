package com.te.golms;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.te.golms.entity.Admin;
import com.te.golms.entity.GoLmsUser;
import com.te.golms.entity.Technology;
import com.te.golms.enums.Gender;
import com.te.golms.repository.AdminRepository;
import com.te.golms.repository.GoLmsUserRepository;
import com.te.golms.repository.TechnologyRepository;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class GolmsApplication {

	private final AdminRepository adminRepository;
	private final GoLmsUserRepository goLmsUserRepository;
	private final TechnologyRepository technologyRepository;
	private final PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(GolmsApplication.class, args);
	}

	@Bean
	public CommandLineRunner saveInDb() {
		return args -> {
			Optional<Admin> findByAdminId = adminRepository.findByAdminId("ADMIN01");
			if (!findByAdminId.isPresent()) {
				Set<String> roles = new HashSet<>();
				roles.add("ROLE_ADMIN");
				Admin admin = new Admin();
				admin.setAdminId("ADMIN01");
				admin.setEmail("thexplorerpro@gmail.com");
				admin.setGender(Gender.MALE);
				admin.setName("ADMIN");
				adminRepository.save(admin);
				GoLmsUser goLmsUser = new GoLmsUser();
				goLmsUser.setUsername(admin.getAdminId());
				goLmsUser.setPassword(passwordEncoder.encode("qwerty"));
				goLmsUser.setAccountActive(true);
				goLmsUser.setPasswordReset(true);
				goLmsUser.setRoles(roles);
				goLmsUserRepository.save(goLmsUser);
			}

			Optional<Technology> javaTech = technologyRepository.findByTechnologyName("Java");
			if (!javaTech.isPresent()) {
				Technology java = new Technology();
				java.setTechnologyName("Java");
				technologyRepository.save(java);
			}

			Optional<Technology> reactTech = technologyRepository.findByTechnologyName("React");
			if (!reactTech.isPresent()) {
				Technology react = new Technology();
				react.setTechnologyName("React");
				technologyRepository.save(react);
			}
		};
	}
}
