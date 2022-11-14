package com.te.spring.boot.security.security;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.te.spring.boot.security.filter.CustomAuthenticationFilter;
import com.te.spring.boot.security.filter.CustomAuthorizationFilter;

import lombok.RequiredArgsConstructor;

/* 
 * Authentication -> Verifies you are who you say you are.
 * Method: 
 * 	1. Login form. 
 *  2. HTTP authentication
 *  3. Custom authentication method
 *  
 * Authorization -> Decides if you have permission to access a resource.
 * Method:
 * 	1. Access Control URLs
 * 	2. Access Control List (ACLs)
 * 
 * */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final UserDetailsService userDetailsService;
	private final BCryptPasswordEncoder passwordEncoder;

	/* This is for Authentication */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

	/* This is for Authorization */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(
				authenticationManagerBean());

		/*
		 * Customizing login url, but it will be blocked by the Spring Security we we
		 * will have to do some configuration.
		 */
		customAuthenticationFilter.setFilterProcessesUrl("/api/login");

		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(STATELESS);

		/* If we don't want to secure certain path, we can use this */
		// http.authorizeRequests().antMatchers("/certainPath").permitAll();

		/*
		 * If we want to make all the resources as public and not secured we can do this
		 */
		// http.authorizeRequests().anyRequest().permitAll();
		http.authorizeRequests().antMatchers("/api/login/**", "/api/token/refresh/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/user/**").hasAnyAuthority("ROLE_USER");
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/user/**").hasAnyAuthority("ROLE_ADMIN");

		/* We want everyone to be authenticated */
		http.authorizeRequests().anyRequest().authenticated();

		/* CustomAuthenticationFilter needs an object of authenticationManager */
		http.addFilter(customAuthenticationFilter);
		http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
	}

}
