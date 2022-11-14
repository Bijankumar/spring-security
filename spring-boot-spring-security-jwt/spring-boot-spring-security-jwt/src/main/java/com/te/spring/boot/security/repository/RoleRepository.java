package com.te.spring.boot.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.spring.boot.security.beans.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByName(String name);
}
