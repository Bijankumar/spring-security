package com.te.spring.boot.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordConfig {
	/*
	 * Note: SHA-1 (Secure Hash Algorithm 1) or greater are hashing algorithm, which
	 * can be combined with an 8-byte or greater randomly generated salt. A salt is
	 * a value that is added to a password (or other secret) which you want to hash
	 * one way. This means it could be before, after, or somewhere inside the
	 * password. Salting is added so identical passwords don't always map to a
	 * single hash value. The salt values have to be generated with a
	 * cryptographically secure random number generator.
	 * 
	 * Here we are using BCryptPasswordEncoder, which is one of the most popular
	 * password encoder.
	 * 
	 * passwordEncoder() method is annotated with @Bean, so that its object gets
	 * created automatically.
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10);
	}
}
