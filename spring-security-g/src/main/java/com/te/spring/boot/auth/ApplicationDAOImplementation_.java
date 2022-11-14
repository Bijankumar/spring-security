package com.te.spring.boot.auth;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.te.spring.boot.security.ApplicationUserRoles;

@Repository("test")
public class ApplicationDAOImplementation_ implements ApplicationUserDAO {

	private final PasswordEncoder passwordEncoder;

	@Autowired
	public ApplicationDAOImplementation_(PasswordEncoder passwordEncoder) {
		super();
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
		return getApplicationUsers().stream().filter(applicationUser -> username.equals(applicationUser.getUsername()))
				.findFirst();
	}

	private List<ApplicationUser> getApplicationUsers() {
		List<ApplicationUser> applicationUsers = Lists.newArrayList(
				new ApplicationUser("admin", passwordEncoder.encode("qwerty"),
						ApplicationUserRoles.ADMIN.getGrantedAuthorities(), true, true, true, true),
				new ApplicationUser("admin_trainee", passwordEncoder.encode("qwerty"),
						ApplicationUserRoles.ADMINTRAINEE.getGrantedAuthorities(), true, true, true, true),
				new ApplicationUser("student01", passwordEncoder.encode("qwerty"),
						ApplicationUserRoles.STUDENT.getGrantedAuthorities(), true, true, true, true));
		return applicationUsers;
	}

}
