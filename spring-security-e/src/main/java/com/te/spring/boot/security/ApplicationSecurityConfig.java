package com.te.spring.boot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

/* Ways of authentication
 * 
 * A. Basic Authentication 
 * 	1. User defined authentication (In memory authentication)
 * 		a. Role based authentication
 * 		b. Permission based authentication
 * 		c. Annotation based authentication on methods
 * 		d. Using customized CSRF token.
 * 
 * A. Basic Authentication 
 * -> In basic authentication we have in include authorization header in every request. 
 * While using basic authentication HTTPS is recommended. 
 * It is simple and fast, but one of the disadvantages of basic authentication is that we cannot logout.
 * If a client sends the request to the server without the authorization header, the client will get 401 unauthorized response.
 * But if the client attached the authorization header with the request, the server then handles on the request.
 * 
 * 1. User defined authentication (In memory authentication)
 * 
 * a. Role based authentication
 * 
 * b. Permission based authentication
 * 
 * c. Annotation based authentication on methods
 * 
 * d. Using customized CSRF token.
 * 
 * CSRF -> Cross Site Request Forgery
 * 
 * Problem when CSRF is disabled:
 * 1. Attacker forges a request for the fund transfer to web site.
 * 2. Attacker enables the request into a hyperlink and send it to visitors who may be logged into the site.
 * 3. A visitor click the link, inadvertently sending the request to the web site.
 * 4. Web site validates the request and transfer funds from the visitor's account to the attacker.
 * 
 * Use of CSRF:
 * 1. When the client logins, server sends a CSRF token to the client.
 * 2. After this whenever the client submits a form to make any POST, PUT or DELETE request, form gets submitted with a CSRF token.
 * 3. Server accepts and work on the request only when the CSRF token in the request matches the token which was sent to the client. 
 * 
 * When to use CSRF protection?
 * CSRF protection should be used for any request that could be processed by a browser by normal user.
 * If you are only creating a service that is used by a non-browser client, then we can disable CSRF protection.
 * 
 * */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // To enable annotation based authentication
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

	private final PasswordEncoder passwordEncoder;

	@Autowired
	public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*
		 * A. Basic Authentication
		 * 
		 * This is the configuration for basic authentication.
		 * 
		 * The problem with basic authentication is user cannot logout.
		 * 
		 * --------- Using this configuration all the apis will be private.
		 * 
		 * http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
		 * 
		 * --------- Using this we can make certain resources as public.
		 * 
		 * http.authorizeRequests().antMatchers("/", "index", "/css/*",
		 * "/js/*").permitAll().anyRequest().authenticated() .and().httpBasic();
		 *
		 *
		 * 1. User defined authentication (In memory authentication)
		 * 
		 * a. Role based authentication
		 * 
		 * --------- Using this below configuration we can make certain apis, only
		 * accessible to a certain roles.
		 * 
		 * Here we are being able to achieve role based authentication.
		 * 
		 * http.authorizeRequests().antMatchers("/", "index", "/css/*",
		 * "/js/*").permitAll().antMatchers("/api/**")
		 * .hasRole(ApplicationUserRoles.STUDENT.name()).anyRequest().authenticated().
		 * and().httpBasic();
		 *
		 *
		 * Disabling CSRF, because Spring Security by default tries to protect our apis.
		 * 
		 * http.csrf().disable().authorizeRequests().antMatchers("/", "index", "/css/*",
		 * "/js/*").permitAll()
		 * .antMatchers("/api/**").hasRole(ApplicationUserRoles.STUDENT.name()).
		 * anyRequest().authenticated().and() .httpBasic();
		 * 
		 *
		 * b. Permission based authentication
		 * 
		 * Here, "/api/**" -> Apis at this path are accessible by STUDENT only.
		 * 
		 * GET apis at "/management/api/**" path are accessible by both ADMIN and
		 * ADMINTRAINEE
		 * 
		 * DELETE, POST and PUT apis are accessible by someone who has permission to
		 * WRITE (Permission based authentication)
		 * 
		 * name() method returns the name of this enum constant as a string, as declared
		 * in the enum declaration.
		 * 
		 * http.csrf().disable().authorizeRequests().antMatchers("/", "index", "/css/*",
		 * "/js/*").permitAll()
		 * .antMatchers("/api/**").hasRole(ApplicationUserRoles.STUDENT.name())
		 * .antMatchers(HttpMethod.GET,
		 * "/management/api/**").hasAnyRole(ApplicationUserRoles.ADMIN.name(),
		 * ApplicationUserRoles.ADMINTRAINEE.name()) .antMatchers(HttpMethod.DELETE,
		 * "/management/api/**").hasAuthority(ApplicationUserPermissions.COURSE_WRITE.
		 * getPermission()) .antMatchers(HttpMethod.POST,
		 * "/management/api/**").hasAuthority(ApplicationUserPermissions.COURSE_WRITE.
		 * getPermission()) .antMatchers(HttpMethod.PUT,
		 * "/management/api/**").hasAuthority(ApplicationUserPermissions.COURSE_WRITE.
		 * getPermission()) .anyRequest() .authenticated().and().httpBasic();
		 *
		 *
		 * c. Annotation based authentication on methods
		 *
		 * No antMatcher() for permission based authentication. Instead annotations are
		 * used.
		 * 
		 * http .csrf().disable() .authorizeRequests().antMatchers("/", "index",
		 * "/css/*", "/js/*").permitAll()
		 * .antMatchers("/api/**").hasRole(ApplicationUserRoles.STUDENT.name()).
		 * anyRequest().authenticated().and() .httpBasic();
		 * 
		 *
		 * d. Using customized CSRF token.
		 * 
		 * By default Spring Security implements CSRF protection.
		 * 
		 * But we can customize it using below code.
		 * 
		 * http
		 * .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).
		 * and() .authorizeRequests().antMatchers("/", "index", "/css/*",
		 * "/js/*").permitAll().antMatchers("/api/**")
		 * .hasRole(ApplicationUserRoles.STUDENT.name()).anyRequest().authenticated().
		 * and().httpBasic();
		 * 
		 */

		http.csrf().disable().authorizeRequests().antMatchers("/", "index", "/css/*", "/js/*").permitAll()
				.antMatchers("/api/**").hasRole(ApplicationUserRoles.STUDENT.name()).anyRequest().authenticated().and()
				.httpBasic();

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
		/*
		 * We have created two roles, but roles are not actually defined. We need to
		 * create a enum of roles and enum for each role's permissions as well.
		 * 
		 * All the users we create will of type UserDetails.
		 */
		UserDetails student01 = User.builder().username("student01").password(passwordEncoder.encode("qwerty"))
				.roles(ApplicationUserRoles.STUDENT.name())
				.authorities(ApplicationUserRoles.STUDENT.getGrantedAuthorities()).build(); // ROLE_STUDENT

		UserDetails admin = User.builder().username("admin").password(passwordEncoder.encode("qwerty"))
				.roles(ApplicationUserRoles.ADMIN.name())
				.authorities(ApplicationUserRoles.ADMIN.getGrantedAuthorities()).build(); // ROLE_ADMIN

		UserDetails adminTrainee = User.builder().username("admin_trainee").password(passwordEncoder.encode("qwerty"))
				.roles(ApplicationUserRoles.ADMINTRAINEE.name())
				.authorities(ApplicationUserRoles.ADMINTRAINEE.getGrantedAuthorities()).build(); // ROLE_ADMINTRAINEE
		/*
		 * Now as in the above code we can see that roles are defined and used in the
		 * role() method using enums.
		 */
		return new InMemoryUserDetailsManager(student01, admin, adminTrainee);
	}
}
