package com.te.spring.boot.security.service;

import java.util.List;

import com.te.spring.boot.security.beans.Role;
import com.te.spring.boot.security.beans.User;

public interface UserService {
	User saveUser(User user);

	Role saveRole(Role role);

	void addRoleToUser(String username, String roleName);

	User getUser(String username);

	List<User> getUsers();
}
