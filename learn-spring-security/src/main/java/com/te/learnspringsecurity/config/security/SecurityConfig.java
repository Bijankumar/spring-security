package com.te.learnspringsecurity.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.te.learnspringsecurity.security.filter.SecurityFilter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final UserDetailsService userDetailsService;
	private final PasswordEncoder passwordEncoder;
	private final SecurityFilter securityFilter;

	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		log.error("AuthenticationManager bean getting created.");
		return super.authenticationManager();
	}

	/* AUTHENTICATION (username + password) */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// UserDetailsService + PasswordEncoder
		log.error("Using UserDetailsService and PasswordEncoder bean in configure(AuthenticationManagerBuilder) method to authentication.");
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

	/* AUTHORIZATION */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		log.error("In configure(HttpSecurity http) for authorization for an api.");
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/api/mentor/**").hasAnyRole("ADMIN", "MENTOR");
		http.authorizeRequests().antMatchers("/api/admin", "/api/admin/**").hasRole("ADMIN");
		http.authorizeRequests().antMatchers("/api/mentor").hasRole("MENTOR");
		http.authorizeRequests().antMatchers("/api/employee", "/api/employee/**").hasRole("EMPLOYEE");
		http.authorizeRequests().antMatchers("/api/login", "/api").permitAll().anyRequest().authenticated();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
	}
}
