package com.te.spring.boot.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/* Ways of authentication
 * 
 * 1. Basic Authentication
 * 
 * */
@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*
		 * This is the configuration for basic authentication.
		 * 
		 * The problem with basic authentication is user cannot logout.
		 * 
		 * Using this configuration all the apis will be private
		 * http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
		 */

		// Using this we can make certain resources as public
		http.authorizeRequests().antMatchers("/", "index", "/css/*", "/js/*").permitAll().anyRequest().authenticated()
				.and().httpBasic();

	}
}
