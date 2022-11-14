package com.te.spring.security.service;

import java.util.List;

import com.te.spring.security.beans.CustomUser;
import com.te.spring.security.beans.Role;

public interface CustomUserService {

	CustomUser register(CustomUser customUser);

	Role saveRole(Role role);

	void addRoleToUser(String email, String roleName);

	CustomUser getUser(String email);

	List<CustomUser> getUsers();
}
