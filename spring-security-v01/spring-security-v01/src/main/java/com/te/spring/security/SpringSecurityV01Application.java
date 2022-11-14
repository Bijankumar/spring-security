package com.te.spring.security;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.te.spring.security.beans.CustomUser;
import com.te.spring.security.beans.Role;
import com.te.spring.security.service.CustomUserService;

@SpringBootApplication
public class SpringSecurityV01Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityV01Application.class, args);
	}

	@Bean
	CommandLineRunner run(CustomUserService customUserService) {
		return args -> {
			customUserService.register(new CustomUser(null, "a@a.com", "A", "A", "qwerty", new ArrayList(), true));
			customUserService.register(new CustomUser(null, "b@b.com", "B", "B", "qwerty", new ArrayList(), true));
			customUserService.register(new CustomUser(null, "c@c.com", "C", "C", "qwerty", new ArrayList(), true));

			customUserService.saveRole(new Role(null, "ROLE_ADMIN"));
			customUserService.saveRole(new Role(null, "ROLE_USER"));

			customUserService.addRoleToUser("a@a.com", "ROLE_ADMIN");
			customUserService.addRoleToUser("b@b.com", "ROLE_USER");
			customUserService.addRoleToUser("c@c.com", "ROLE_ADMIN");
			customUserService.addRoleToUser("c@c.com", "ROLE_USER");
		};
	}
}
