package com.te.spring.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.spring.security.beans.CustomUser;

public interface CustomUserRepository extends JpaRepository<CustomUser, Long> {
	CustomUser findByEmail(String email);
}
