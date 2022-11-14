package com.te.learnspringsecurity.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class AppConfig {
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		log.error("In getPasswordEncoder() method!");
		return new BCryptPasswordEncoder();
	}
	
}
