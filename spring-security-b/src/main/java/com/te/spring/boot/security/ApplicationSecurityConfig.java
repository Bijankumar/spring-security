package com.te.spring.boot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/* Ways of authentication
 * 
 * 1. Basic Authentication
 * 
 * 2. User defined authentication (In memory authentication)
 * */
@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

	private final PasswordEncoder passwordEncoder;

	@Autowired
	public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*
		 * 1. Basic Authentication
		 * 
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

	/*
	 * 2. User defined authentication (In memory authentication)
	 * 
	 * When we restart the server, we loose the user credentials. Credentials get
	 * re-created each time server is re-run.
	 * 
	 * So now what we can do is, we can store the user details in the database. User
	 * will will have a unique user name. password (encoded), roles (ROLE_NAME, to
	 * control the end points they are allowed), authorities/permissions and more.
	 * 
	 * To create a in memory user, we need to override the userDetailsServiceBean()
	 * method present in the WebSecurityConfigurerAdapter abstract class.
	 * 
	 * As we have used @Bean annotation, the object of UserDetailsService interface
	 * will be instantiated.
	 * 
	 * In the role() method we have provided SOME_ROLE_NAME as role, but Spring
	 * Security will make it ROLE_SOME_ROLE_NAME and will use it throughout the
	 * application.
	 * 
	 * After creating this method when user credentials are provided in the form, we
	 * see an exception i.e. java.lang.IllegalArgumentException: There is no
	 * PasswordEncoder mapped for the id "null". Because the password is not
	 * encoded.
	 * 
	 * For this we will have to create a configuration class to encode the password.
	 * 
	 * After creating the configuration class and the method that will give the
	 * PasswordEncoder method, we will get a log saying
	 * o.s.s.c.bcrypt.BCryptPasswordEncoder: Encoded password does not look like
	 * BCrypt. Because we have not applied the password encoder on the password.
	 * 
	 * For that we have to add this to the code below:
	 * .password(passwordEncoder.encode("password"))
	 * 
	 */
	@Override
	@Bean
	public UserDetailsService userDetailsServiceBean() throws Exception {
		UserDetails student01 = User.builder().username("student01").password(passwordEncoder.encode("student01"))
				.roles("STUDENT").build();
		return new InMemoryUserDetailsManager(student01);
	}
}
