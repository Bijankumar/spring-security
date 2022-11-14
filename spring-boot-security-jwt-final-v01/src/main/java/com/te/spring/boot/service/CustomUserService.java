package com.te.spring.boot.service;

import java.util.Optional;

import com.te.spring.boot.beans.CustomUser;

public interface CustomUserService {
	String saveUser(CustomUser customUser);

	Optional<CustomUser> findByUsername(String username);
}
