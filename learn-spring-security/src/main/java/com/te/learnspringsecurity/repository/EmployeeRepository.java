package com.te.learnspringsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.learnspringsecurity.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

}
