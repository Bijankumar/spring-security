package com.te.spring.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.te.spring.boot.beans.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

	Department findByDepartmentName(String departmentName);
	
	Department findByDepartmentNameIgnoreCase(String departmentName);
	
	@Query("select d from Department d where d.departmentAddress = ?1")
	Department findByDepartmentAddress(String departmentAddress);
	 
}
