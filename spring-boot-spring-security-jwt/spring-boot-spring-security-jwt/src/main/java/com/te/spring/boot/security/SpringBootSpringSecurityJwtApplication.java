package com.te.spring.boot.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.te.spring.boot.security.beans.Role;
import com.te.spring.boot.security.beans.User;
import com.te.spring.boot.security.service.UserService;

@SpringBootApplication
public class SpringBootSpringSecurityJwtApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSpringSecurityJwtApplication.class, args);
		System.out.println("Application started!");
	}

	@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {
			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));
			userService.saveRole(new Role(null, "ROLE_MANAGER"));
			userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

			userService.saveUser(new User(null, "User01", "username01", "qwerty", new ArrayList<>()));
			userService.saveUser(new User(null, "User02", "username02", "qwerty", new ArrayList<>()));
			userService.saveUser(new User(null, "User03", "username03", "qwerty", new ArrayList<>()));
			userService.saveUser(new User(null, "User04", "username04", "qwerty", new ArrayList<>()));

			userService.addRoleToUser("username01", "ROLE_USER");
			userService.addRoleToUser("username01", "ROLE_MANAGER");
			userService.addRoleToUser("username02", "ROLE_ADMIN");
			userService.addRoleToUser("username03", "ROLE_MANAGER");
			userService.addRoleToUser("username04", "ROLE_SUPER_ADMIN");

		};
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
