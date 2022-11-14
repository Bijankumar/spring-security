package com.te.golms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.te.golms.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
	@Query("SELECT e FROM Employee e where e.email =:email")
	public Optional<Employee> findByEmail(String email);

	public Optional<Employee> findByEmpId(String empId);
}
