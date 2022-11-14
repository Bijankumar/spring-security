package com.te.spring.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.spring.security.beans.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	Role findByRoleName(String roleName);
}
