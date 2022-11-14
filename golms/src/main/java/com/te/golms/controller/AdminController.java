package com.te.golms.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.golms.entity.model.AddEmployeeToBatchModel;
import com.te.golms.entity.model.BatchModel;
import com.te.golms.entity.model.MentorModel;
import com.te.golms.entity.model.RejectRegisterRequestModel;
import com.te.golms.exception.BatchNotDeletedException;
import com.te.golms.exception.BatchNotSavedException;
import com.te.golms.exception.BatchNotUpdatedException;
import com.te.golms.exception.EmployeeNotAddedToBatchException;
import com.te.golms.exception.EmployeeRegistrationNotRejected;
import com.te.golms.exception.MentorNotDeletedException;
import com.te.golms.exception.MentorNotSavedException;
import com.te.golms.response.GeneralResponse;
import com.te.golms.service.AdminService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/admin")
@RequiredArgsConstructor
@CrossOrigin("*")
@Validated
public class AdminController {

	private final AdminService adminService;

	/* All GET methods! */

	@GetMapping(path = "/batches")
	public ResponseEntity<GeneralResponse> getBatchList() {
		return ResponseEntity.ok().body(new GeneralResponse(HttpStatus.OK, null, "Batch list is provided!", null, null,
				adminService.getBatcheList()));
	}

	@GetMapping(path = "/requests")
	public ResponseEntity<GeneralResponse> getRequestList() {
		return ResponseEntity.ok().body(new GeneralResponse(HttpStatus.OK, null,
				"Registration request list is provided!", null, null, adminService.getRequestList()));
	}

	@GetMapping(path = "/mentors")
	public ResponseEntity<GeneralResponse> getMentorList() {
		return ResponseEntity.ok().body(new GeneralResponse(HttpStatus.OK, null, "Mentors list is provided!", null,
				null, adminService.getMentorList()));

	}

	/* All POST methods! */

	@PostMapping(path = "/batch")
	public ResponseEntity<GeneralResponse> saveBatch(@RequestBody BatchModel batchModel) {
		BatchModel saveBatch = adminService.saveBatch(batchModel);
		if (saveBatch != null) {
			return ResponseEntity.accepted()
					.body(new GeneralResponse(HttpStatus.ACCEPTED, null, "Batch is added!", null, null, saveBatch));
		}
		throw new BatchNotSavedException(
				"Batch data could not be saved, please check the data provided. It may happen because mentor or technology data is not present in the database!");

	}

	@PostMapping(path = "/mentor")
	public ResponseEntity<GeneralResponse> saveMentor(@RequestBody MentorModel mentorModel) {
		MentorModel saveMentor = adminService.saveMentor(mentorModel);
		if (saveMentor != null) {
			return ResponseEntity.accepted()
					.body(new GeneralResponse(HttpStatus.ACCEPTED, null, "Mentor is added!", null, null, saveMentor));
		}
		throw new MentorNotSavedException("Mentor data could not be saved, please check the data provided!");

	}

	/* All PUT methods! */

	@PutMapping(path = "/batch")
	public ResponseEntity<GeneralResponse> updateBatch(@RequestBody BatchModel batchModel) {
		BatchModel updateBatch = adminService.updateBatch(batchModel);
		if (updateBatch != null) {
			return ResponseEntity.accepted().body(
					new GeneralResponse(HttpStatus.ACCEPTED, null, "Batch data is updated!", null, null, updateBatch));
		}
		throw new BatchNotUpdatedException(
				"Batch data could not be saved, please check the data provided. It may happen because mentor or technology data is not present in the database!");

	}

	@PutMapping(path = "/approve")
	public ResponseEntity<GeneralResponse> addEmployeeToBatchModel(
			@RequestBody AddEmployeeToBatchModel addEmployeeToBatchModel) {
		boolean isAdded = adminService.addEmployeeToBatchModel(addEmployeeToBatchModel);
		if (isAdded) {
			return ResponseEntity.accepted().body(new GeneralResponse(HttpStatus.ACCEPTED, null,
					"Employee is added to the batch!", null, null, isAdded));
		}
		throw new EmployeeNotAddedToBatchException(
				"Employee could not be added to batch, please check the data provided!");

	}

	@PutMapping(path = "/reject")
	public ResponseEntity<GeneralResponse> rejectRequest(@RequestBody RejectRegisterRequestModel rejectRequest) {
		boolean request = adminService.rejectRequest(rejectRequest);
		if (request) {
			return ResponseEntity.ok()
					.body(new GeneralResponse(HttpStatus.OK, null, "Request is rejected!", null, null, request));
		}
		throw new EmployeeRegistrationNotRejected(
				"Employee registraction could not be rejected, please check the data provided!");

	}

	/* All DELETE methods! */

	@DeleteMapping(path = "/batch/{batchName}")
	public ResponseEntity<GeneralResponse> deleteBatch(@PathVariable String batchName) {
		try {
			boolean deleteBatch = adminService.deleteBatch(batchName);
			if (deleteBatch) {
				return ResponseEntity.ok()
						.body(new GeneralResponse(HttpStatus.OK, null, "Batch is deleted!", null, null, deleteBatch));
			}
		} catch (Exception e) {
			throw new BatchNotDeletedException("Batch could not be deleted, please check the data provided!");
		}
		throw new BatchNotDeletedException("Batch could not be deleted, please check the data provided!");

	}

	@DeleteMapping(path = "/mentor/{mentorId}")
	public ResponseEntity<GeneralResponse> deleteMentor(@PathVariable String mentorId) {
		try {
			boolean deleteMentor = adminService.deleteMentor(mentorId);
			if (deleteMentor) {
				return ResponseEntity.ok()
						.body(new GeneralResponse(HttpStatus.OK, null, "Mentor is deleted!", null, null, deleteMentor));
			}
		} catch (Exception e) {
			throw new MentorNotDeletedException("Mentor could not be deleted, please check the data provided!");
		}
		throw new MentorNotDeletedException("Mentor could not be deleted, please check the data provided!");
	}

}
