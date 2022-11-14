package com.te.golms.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.golms.entity.Employee;
import com.te.golms.entity.MockDetails;
import com.te.golms.entity.model.EmployeeModel;
import com.te.golms.exception.EmployeeDetailsNotUpdatedException;
import com.te.golms.response.GeneralResponse;
import com.te.golms.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/employee")
@RequiredArgsConstructor
@CrossOrigin("*")
@Validated
public class EmployeeController {

	private final EmployeeService employeeService;

	/* All GET methods! */

	@GetMapping(path = "/{empId}")
	public ResponseEntity<GeneralResponse> getEmpDetails(@PathVariable String empId) {
		Employee empDetails = employeeService.getEmpDetails(empId);
		if (empDetails.getInBatch() != null) {
			empDetails.getInBatch().setEmployees(null);
		}
		for (MockDetails mockDetails : empDetails.getMockDetails()) {
			mockDetails.setEmployee(null);
		}
		return ResponseEntity.ok().body(new GeneralResponse(HttpStatus.OK, null, "Employee details are provided!",
				null, null, empDetails));
	}

	/* All PUT methods! */

	@PutMapping(path = "/")
	public ResponseEntity<GeneralResponse> updateEmpInfo(@RequestBody EmployeeModel employeeModel) {
		boolean isUpdated = employeeService.updateEmpDetails(employeeModel.getEmpId(), employeeModel);
		if (isUpdated) {
			return ResponseEntity.accepted().body(new GeneralResponse(HttpStatus.ACCEPTED, null,
					"Employee details are updated!", null, null, isUpdated));
		}
		throw new EmployeeDetailsNotUpdatedException("Employee details could not be updated!");
	}
}
