package com.te.golms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.golms.entity.GoLmsUser;

public interface GoLmsUserRepository extends JpaRepository<GoLmsUser, Integer> {
	public abstract Optional<GoLmsUser> findByUsername(String username);
}
