package com.te.learnspringsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.learnspringsecurity.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, String>{

}
