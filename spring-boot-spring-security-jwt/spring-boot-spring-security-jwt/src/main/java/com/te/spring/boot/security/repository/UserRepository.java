package com.te.spring.boot.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.spring.boot.security.beans.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
}
