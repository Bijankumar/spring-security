package com.te.spring.boot.security;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.te.spring.boot.auth.ApplicationUserService;

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
 * In configure method we have added .httpBasic(); method at the end which means basic authentication.
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
 * 
 * 
 * 
 * B. Form Based Authentication
 * -> Form based authentication is widely used in web sites. It allow us to use user name and password. 
 * We have full control how we have create the form. We can also logout here which was not possible in basic authentication. 
 * And here also HTTPS is recommended.
 * 
 * Client will POST the user name and password from the form. 
 * Server will validate the credentials and send the status 200 OK if the credentials are valid.
 * Server will also send a cookie session id to the client browser. 
 * Next time whenever the client will make the the request it will also send the session id with the request, which was sent by the server.
 * And if the session id received with the request is valid, server will handle the request.
 * A session id is usually valid for 30 minutes, but we can also customize it.
 * 
 * a. Using default form provided by Spring Security
 * 
 * b. Redirecting to other page after form submit
 * 
 * c. Remember me feature
 * 
 * d. Customized logout feature
 * 
 * e. Changing the name attribute in the login form
 * 
 * 
 * 
 * 
 * C. Database authentication
 * */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // To enable annotation based authentication
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

	private final PasswordEncoder passwordEncoder;
	private final ApplicationUserService applicationUserService;

	@Autowired
	public ApplicationSecurityConfig(PasswordEncoder passwordEncoder, ApplicationUserService applicationUserService) {
		this.passwordEncoder = passwordEncoder;
		this.applicationUserService = applicationUserService;
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

		/*
		 * B. Form based Authentication
		 * 
		 * In the below code CSRF is disabled, so we are not looking at CSRF token. But
		 * we are using authentication token which we get by default. By using
		 * .formLogin(); at the end we are being able to use form based authentication.
		 * Spring security provides a form to login using user name and password.
		 * 
		 * a. Using default form provided by Spring Security
		 * 
		 * http.csrf().disable().authorizeRequests().antMatchers("/", "index", "/css/*",
		 * "/js/*").permitAll()
		 * .antMatchers("/api/**").hasRole(ApplicationUserRoles.STUDENT.name()).
		 * anyRequest().authenticated().and() .formLogin();
		 * 
		 * To use custom login page we have to add .loginPage("/login"); to the end.
		 * Because of which we will not be able to see the custom login page.
		 * 
		 * We created a login page, a Controller for handling the views and added
		 * thymeleaf dependency in pom.xml. Created controller methods to handle the
		 * requests.
		 * 
		 * To make login api easily accessible to everyone we have to put
		 * .formLogin().loginPage("/login").permitAll()
		 * 
		 * b. Redirecting to other page after form submit
		 * 
		 * When we submit the custom login form we get redirected to the index.html. To
		 * customize redirection of the page we can use this
		 * .formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/courses",
		 * true);
		 * 
		 * http.csrf().disable().authorizeRequests().antMatchers("/", "index", "/css/*",
		 * "/js/*").permitAll()
		 * .antMatchers("/api/**").hasRole(ApplicationUserRoles.STUDENT.name()).
		 * anyRequest().authenticated().and()
		 * .formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/courses",
		 * true);
		 * 
		 * c. Remember me feature
		 * 
		 * To add remember me feature we can use this .rememberMe(); method at the end.
		 * By default i.e. without .rememberMe(); method, a session id expires after 30
		 * minutes. But when using .rememberMe(); method, a session id expires after 2
		 * weeks.
		 * 
		 * http.csrf().disable().authorizeRequests().antMatchers("/", "index", "/css/*",
		 * "/js/*").permitAll()
		 * .antMatchers("/api/**").hasRole(ApplicationUserRoles.STUDENT.name()).
		 * anyRequest().authenticated().and()
		 * .formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/courses",
		 * true).and().rememberMe();
		 * 
		 * We can also customize the amount of time the session id is valid. We have to
		 * add .rememberMe() .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
		 * .key("this_is_for_hashing_of_username_and_expiration_time"); at the end.
		 * 
		 * d. Customized logout feature
		 * 
		 * We can customize logout procedure such that cookies and all the other
		 * information gets deleted from the client browser by adding these methods
		 * 
		 * .logout().logoutUrl("/logout")
		 * .clearAuthentication(true).invalidateHttpSession(true).deleteCookies(
		 * "JSESSIONID", "remember-me") .logoutSuccessUrl("/login");
		 * 
		 * The URL that triggers logout to occur (default is "/logout"). If the CSRF
		 * protection is enabled (default), then the request must also be POST. This
		 * means by default POST "/logout" is required to trigger a logout.If CSRF
		 * protection is disabled then any HTTP method is allowed. It is a best practice
		 * to use HTTP POST on any action that changes state (i.e. logout) to protect
		 * against CSRF attack.
		 * 
		 * If you want to use an HTTP GET you can use .logoutRequestMatcher(new
		 * AntPathRequestMatcher("/logout", "GET")) method.
		 * 
		 * http.csrf().disable().authorizeRequests().antMatchers("/", "index", "/css/*",
		 * "/js/*").permitAll()
		 * .antMatchers("/api/**").hasRole(ApplicationUserRoles.STUDENT.name()).
		 * anyRequest().authenticated().and()
		 * .formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/courses",
		 * true) .and().rememberMe().tokenValiditySeconds((int)
		 * TimeUnit.DAYS.toSeconds(21))
		 * .key("this_is_for_hashing_of_username_and_expiration_time").
		 * .and().logout().logoutUrl("/logout").logoutRequestMatcher(new
		 * AntPathRequestMatcher("/logout", "GET"))
		 * .clearAuthentication(true).invalidateHttpSession(true).deleteCookies(
		 * "JSESSIONID", "remember-me") .logoutSuccessUrl("/login");
		 * 
		 * e. Changing the name attribute in the login form
		 * 
		 * <input type="text" id="username" name="username" class="form-control"
		 * placeholder="Username" required="" autofocus=""> Like in the above input
		 * field, if we want to change the name attribute of password, username ande
		 * remember me parameter, we can make use of methods like
		 * .passwordParameter("...") .usernameParameter("...")
		 * .rememberMeParameter("...").
		 * 
		 * http.csrf().disable().authorizeRequests().antMatchers("/", "index", "/css/*",
		 * "/js/*").permitAll()
		 * .antMatchers("/api/**").hasRole(ApplicationUserRoles.STUDENT.name()).
		 * anyRequest().authenticated().and()
		 * .formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/courses",
		 * true) .passwordParameter("password").usernameParameter("username")
		 * 
		 * .and().rememberMe().tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
		 * .key("this_is_for_hashing_of_username_and_expiration_time").
		 * rememberMeParameter("remember-me")
		 * 
		 * .and().logout().logoutUrl("/logout").logoutRequestMatcher(new
		 * AntPathRequestMatcher("/logout", "GET"))
		 * .clearAuthentication(true).invalidateHttpSession(true).deleteCookies(
		 * "JSESSIONID", "remember-me") .logoutSuccessUrl("/login");
		 * 
		 * 
		 */

		http.csrf().disable().authorizeRequests().antMatchers("/", "index", "/css/*", "/js/*").permitAll()
				.antMatchers("/api/**").hasRole(ApplicationUserRoles.STUDENT.name()).anyRequest().authenticated().and()
				.formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/courses", true)
				.passwordParameter("password").usernameParameter("username")

				.and().rememberMe().tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
				.key("this_is_for_hashing_of_username_and_expiration_time").rememberMeParameter("remember-me")

				.and().logout().logoutUrl("/logout").logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
				.clearAuthentication(true).invalidateHttpSession(true).deleteCookies("JSESSIONID", "remember-me")
				.logoutSuccessUrl("/login");

	}

	/*
	 * 1. User defined authentication (In memory authentication)
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

	// @Override
	// @Bean
	// public UserDetailsService userDetailsServiceBean() throws Exception {
	// /*
	// * We have created two roles, but roles are not actually defined. We need to
	// * create a enum of roles and enum for each role's permissions as well.
	// *
	// * All the users we create will of type UserDetails.
	// */
	// UserDetails student01 =
	// User.builder().username("student01").password(passwordEncoder.encode("qwerty"))
	// .roles(ApplicationUserRoles.STUDENT.name())
	// .authorities(ApplicationUserRoles.STUDENT.getGrantedAuthorities()).build();
	// // ROLE_STUDENT
	//
	// UserDetails admin =
	// User.builder().username("admin").password(passwordEncoder.encode("qwerty"))
	// .roles(ApplicationUserRoles.ADMIN.name())
	// .authorities(ApplicationUserRoles.ADMIN.getGrantedAuthorities()).build(); //
	// ROLE_ADMIN
	//
	// UserDetails adminTrainee =
	// User.builder().username("admin_trainee").password(passwordEncoder.encode("qwerty"))
	// .roles(ApplicationUserRoles.ADMINTRAINEE.name())
	// .authorities(ApplicationUserRoles.ADMINTRAINEE.getGrantedAuthorities()).build();
	// // ROLE_ADMINTRAINEE
	// /*
	// * Now as in the above code we can see that roles are defined and used in the
	// * role() method using enums.
	// */
	// return new InMemoryUserDetailsManager(student01, admin, adminTrainee);
	// }

	/*
	 * C. Database authentication
	 * 
	 */

	@Override
	protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) {
		authenticationManagerBuilder.authenticationProvider(daoAuthenticationProvider());
	}

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setPasswordEncoder(passwordEncoder);
		authenticationProvider.setUserDetailsService(applicationUserService);
		return authenticationProvider;
	}
}
