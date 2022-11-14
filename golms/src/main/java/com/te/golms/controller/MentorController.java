package com.te.golms.controller;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.golms.entity.MockDetailsModel;
import com.te.golms.entity.model.AttendenceInfoModel;
import com.te.golms.entity.model.BatchModel;
import com.te.golms.entity.model.EmpStatusModel;
import com.te.golms.entity.model.MockModel;
import com.te.golms.exception.EmployeeAttendenceNotUpdatedException;
import com.te.golms.exception.EmployeeStatusNotChangedException;
import com.te.golms.exception.MockNotCreatedException;
import com.te.golms.exception.MockRatingNotAddedException;
import com.te.golms.response.GeneralResponse;
import com.te.golms.service.EmployeeService;
import com.te.golms.service.MentorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/mentor")
@RequiredArgsConstructor
@CrossOrigin("*")
public class MentorController {

	private final MentorService mentorService;
	private final EmployeeService employeeService;

	/* All GET methods! */

	@GetMapping(path = "/batches")
	public ResponseEntity<GeneralResponse> getBatch(Principal principal) {
		String mentorId = principal.getName();
		BatchModel batchData = mentorService.getBatchData(mentorId);
		if (batchData != null) {
			return ResponseEntity.ok().body(new GeneralResponse(HttpStatus.OK, null, "Mentor's batch data is provided!",
					null, null, batchData));
		}
		return ResponseEntity.ok().body(
				new GeneralResponse(HttpStatus.OK, null, "Mentor not assigned to a batch!", null, null, batchData));
	}

	/* All POST methods! */

	@PostMapping(path = "/batches/mock/rating/{empId}")
	public ResponseEntity<GeneralResponse> mockRating(@PathVariable String empId,
			@RequestBody MockDetailsModel mockDetailsModel) {
		boolean isAdded = mentorService.addEmpMockData(empId, mockDetailsModel);
		if (isAdded) {
			return ResponseEntity.accepted()
					.body(new GeneralResponse(HttpStatus.ACCEPTED, null, "Mock rating is added!", null, null, isAdded));
		}
		throw new MockRatingNotAddedException("Mock rating could not be added, please check the data provided!");
	}

	@PostMapping(path = "/batches/employee/attendence")
	public ResponseEntity<GeneralResponse> attendence(@RequestBody AttendenceInfoModel attendenceInfoModel) {
		boolean attendenceAdded = employeeService.addAttendenceInfo(attendenceInfoModel);
		if (attendenceAdded) {
			return ResponseEntity.accepted().body(new GeneralResponse(HttpStatus.ACCEPTED, null,
					"Employee attendence data is added!", null, null, attendenceAdded));
		}
		throw new EmployeeAttendenceNotUpdatedException(
				"Employee attendence could not be updated, please check the data provided!");

	}

	@PostMapping(path = "/mock/{mentorId}")
	public ResponseEntity<GeneralResponse> createNewMock(@PathVariable String mentorId,
			@RequestBody MockModel mockModel) {
		boolean isMockCreated = mentorService.createNewMock(mentorId, mockModel);
		if (isMockCreated) {
			return ResponseEntity.accepted().body(
					new GeneralResponse(HttpStatus.ACCEPTED, null, "Mock is created!", null, null, isMockCreated));
		}
		throw new MockNotCreatedException(
				"Mock was not created, employees were not mailed about the upcomming mock, please check the data provided!");

	}

	/* All PUT methods! */

	@PutMapping(path = "/batches/employee/status")
	public ResponseEntity<GeneralResponse> changeEmployeeStatus(@RequestBody EmpStatusModel empStatusModel) {
		boolean isStatusChanged = mentorService.changeEmployeeStatus(empStatusModel);
		if (isStatusChanged) {
			return ResponseEntity.ok().body(new GeneralResponse(HttpStatus.OK, null, "Employee status is changed!",
					null, null, isStatusChanged));
		}
		throw new EmployeeStatusNotChangedException(
				"Employee status could not be changed, please check the data provided!");
	}
}
