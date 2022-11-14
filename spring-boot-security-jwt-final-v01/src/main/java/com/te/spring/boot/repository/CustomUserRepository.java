package com.te.spring.boot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.spring.boot.beans.CustomUser;

public interface CustomUserRepository extends JpaRepository<CustomUser, Integer> {
	Optional<CustomUser> findByUsername(String username);
}
