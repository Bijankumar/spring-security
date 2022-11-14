package com.te.spring.boot.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.spring.boot.beans.Department;
import com.te.spring.boot.custom.exceptions.DepartmentNotFoundException;
import com.te.spring.boot.service.DepartmentService;

@RestController
@RequestMapping(path = "/department")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;
	
	private final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

	@PostMapping(path = "/save")
	public Department saveDepartmentData(@Valid @RequestBody Department department) {
		LOGGER.info("Saving department data!");
		return departmentService.saveDepartmentData(department);
	}

	@GetMapping(path = "/list")
	public List<Department> fetchDepartmentList() {
		LOGGER.info("Fetching all the department data!");
		return departmentService.fetchDepartmentList();
	}

	@GetMapping(path = "/get/{departmentId}")
	public Department fetchDepartmentById(@PathVariable("departmentId") Long departmentId) throws DepartmentNotFoundException {
		LOGGER.info("Fetching the department data!");
		return departmentService.fetchDepartmentById(departmentId);
	}

	@DeleteMapping(path = "/delete/{departmentId}")
	public Boolean deleteDepartmentById(@PathVariable("departmentId") Long departmentId) {
		LOGGER.info("Deleting the department data!");
		departmentService.deleteDepartmentById(departmentId);
		return true;
	}

	@PutMapping(path = "/update/{departmentId}")
	public Department updateDepartment(@PathVariable("departmentId") Long departmentId,
			@RequestBody Department department) {
		LOGGER.info("Updating the department data!");
		return departmentService.updateDepartment(departmentId, department);
	}

	@GetMapping(path = "/get/name/{departmentName}")
	public Department fetchDepartmentByName(@PathVariable("departmentName") String departmentName) {
		LOGGER.info("Fetching the department data by name!");
		return departmentService.fetchDepartmentByName(departmentName);
	}

	@GetMapping(path = "/get/address/{departmentAddress}")
	public Department fetchDepartmentByAddress(String departmentAddress) {
		LOGGER.info("Fetching the department data by address!");
		return departmentService.fetchDepartmentByAddress(departmentAddress);
	}
}
