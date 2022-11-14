package com.te.spring.boot.service;

import java.util.List;

import com.te.spring.boot.beans.Department;
import com.te.spring.boot.custom.exceptions.DepartmentNotFoundException;

public interface DepartmentService {

	Department saveDepartmentData(Department department);

	List<Department> fetchDepartmentList();

	Department fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException;

	void deleteDepartmentById(Long departmentId);

	Department updateDepartment(Long departmentId, Department department);

	Department fetchDepartmentByName(String departmentName);

	Department fetchDepartmentByAddress(String departmentAddress);

}
