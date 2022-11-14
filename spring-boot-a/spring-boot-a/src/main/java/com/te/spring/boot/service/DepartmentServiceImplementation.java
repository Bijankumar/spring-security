package com.te.spring.boot.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.te.spring.boot.beans.Department;
import com.te.spring.boot.custom.exceptions.DepartmentNotFoundException;
import com.te.spring.boot.repository.DepartmentRepository;

@Service
public class DepartmentServiceImplementation implements DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public Department saveDepartmentData(Department department) {
		return departmentRepository.save(department);
	}

	@Override
	public List<Department> fetchDepartmentList() {
		return departmentRepository.findAll();
	}

	@Override
	public Department fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException {
		Optional<Department> depOptional = departmentRepository.findById(departmentId);
		if (!depOptional.isPresent()) {
			throw new DepartmentNotFoundException("Department not found exception!");
		}
		return depOptional.get();
	}

	@Override
	public void deleteDepartmentById(Long departmentId) {
		departmentRepository.deleteById(departmentId);
	}

	@Override
	public Department updateDepartment(Long departmentId, Department department) {
		Department departmentFromDB = departmentRepository.findById(departmentId).get();
		if (Objects.nonNull(departmentFromDB)) {
			if (!"".equalsIgnoreCase(department.getDepartmentName()))
				departmentFromDB.setDepartmentName(department.getDepartmentName());
			if (!"".equalsIgnoreCase(department.getDepartmentAddress()))
				departmentFromDB.setDepartmentAddress(department.getDepartmentAddress());
		}
		return departmentRepository.save(departmentFromDB);
	}

	@Override
	public Department fetchDepartmentByName(String departmentName) {
		return departmentRepository.findByDepartmentNameIgnoreCase(departmentName);
	}

	@Override
	public Department fetchDepartmentByAddress(String departmentAddress) {
		return departmentRepository.findByDepartmentAddress(departmentAddress);
	}

}
