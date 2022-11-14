package com.te.spring.security.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.te.spring.security.beans.CustomUser;
import com.te.spring.security.beans.Role;
import com.te.spring.security.repository.CustomUserRepository;
import com.te.spring.security.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomUserServiceImplementation implements CustomUserService {

	private final CustomUserRepository customUserRepository;
	private final RoleRepository roleRepository;

	@Override
	public CustomUser register(CustomUser customUser) {
		System.out.println("In service register()!");
		System.out.println(customUser);
		return customUserRepository.save(customUser);
	}

	@Override
	public Role saveRole(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public void addRoleToUser(String email, String roleName) {
		CustomUser customUser = customUserRepository.findByEmail(email);
		Role role = roleRepository.findByRoleName(roleName);
		customUser.getRoles().add(role);
	}

	@Override
	public CustomUser getUser(String email) {
		return customUserRepository.findByEmail(email);
	}

	@Override
	public List<CustomUser> getUsers() {
		return customUserRepository.findAll();
	}

}
