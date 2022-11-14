package com.te.golms.exception.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.te.golms.exception.BatchNotDeletedException;
import com.te.golms.exception.BatchNotSavedException;
import com.te.golms.exception.BatchNotUpdatedException;
import com.te.golms.exception.EmployeeAttendenceNotUpdatedException;
import com.te.golms.exception.EmployeeDetailsNotUpdatedException;
import com.te.golms.exception.EmployeeNotAddedToBatchException;
import com.te.golms.exception.EmployeeRegistrationNotRejected;
import com.te.golms.exception.EmployeeStatusNotChangedException;
import com.te.golms.exception.MentorNotDeletedException;
import com.te.golms.exception.MentorNotSavedException;
import com.te.golms.exception.MockNotCreatedException;
import com.te.golms.exception.MockRatingNotAddedException;
import com.te.golms.exception.PasswordNotResetException;
import com.te.golms.exception.UserAlreadyExistException;
import com.te.golms.exception.WrongCredentialsOrAccountNotActiveException;
import com.te.golms.response.GeneralResponse;

@ControllerAdvice
public class GoLmsExceptionHandler {

	@ExceptionHandler(value = { UserAlreadyExistException.class })
	public ResponseEntity<Object> handler(UserAlreadyExistException e) {
		return ResponseEntity.badRequest()
				.body(new GeneralResponse(HttpStatus.BAD_REQUEST, e.getMessage(), null, false, null, null));
	}

	@ExceptionHandler(value = { EmployeeNotAddedToBatchException.class })
	public ResponseEntity<Object> handler(EmployeeNotAddedToBatchException e) {
		return ResponseEntity.badRequest()
				.body(new GeneralResponse(HttpStatus.BAD_REQUEST, e.getMessage(), null, false, null, null));
	}

	@ExceptionHandler(value = { EmployeeAttendenceNotUpdatedException.class })
	public ResponseEntity<Object> handler(EmployeeAttendenceNotUpdatedException e) {
		return ResponseEntity.badRequest()
				.body(new GeneralResponse(HttpStatus.BAD_REQUEST, e.getMessage(), null, null, null, null));
	}

	@ExceptionHandler(value = { EmployeeRegistrationNotRejected.class })
	public ResponseEntity<Object> handler(EmployeeRegistrationNotRejected e) {
		return ResponseEntity.badRequest()
				.body(new GeneralResponse(HttpStatus.BAD_REQUEST, e.getMessage(), null, null, null, null));
	}

	@ExceptionHandler(value = { EmployeeDetailsNotUpdatedException.class })
	public ResponseEntity<Object> handler(EmployeeDetailsNotUpdatedException e) {
		return ResponseEntity.badRequest()
				.body(new GeneralResponse(HttpStatus.BAD_REQUEST, e.getMessage(), null, null, null, null));
	}

	@ExceptionHandler(value = { EmployeeStatusNotChangedException.class })
	public ResponseEntity<Object> handler(EmployeeStatusNotChangedException e) {
		return ResponseEntity.badRequest()
				.body(new GeneralResponse(HttpStatus.BAD_REQUEST, e.getMessage(), null, null, null, null));
	}

	@ExceptionHandler(value = { MentorNotDeletedException.class })
	public ResponseEntity<Object> handler(MentorNotDeletedException e) {
		return ResponseEntity.badRequest()
				.body(new GeneralResponse(HttpStatus.BAD_REQUEST, e.getMessage(), null, null, null, null));
	}

	@ExceptionHandler(value = { MentorNotSavedException.class })
	public ResponseEntity<Object> handler(MentorNotSavedException e) {
		return ResponseEntity.badRequest()
				.body(new GeneralResponse(HttpStatus.BAD_REQUEST, e.getMessage(), null, null, null, null));
	}

	@ExceptionHandler(value = { MockRatingNotAddedException.class })
	public ResponseEntity<Object> handler(MockRatingNotAddedException e) {
		return ResponseEntity.badRequest()
				.body(new GeneralResponse(HttpStatus.BAD_REQUEST, e.getMessage(), null, null, null, null));
	}

	@ExceptionHandler(value = { MockNotCreatedException.class })
	public ResponseEntity<Object> handler(MockNotCreatedException e) {
		return ResponseEntity.badRequest()
				.body(new GeneralResponse(HttpStatus.BAD_REQUEST, e.getMessage(), null, null, null, null));
	}

	@ExceptionHandler(value = { WrongCredentialsOrAccountNotActiveException.class })
	public ResponseEntity<Object> handler(WrongCredentialsOrAccountNotActiveException e) {
		return ResponseEntity.badRequest()
				.body(new GeneralResponse(HttpStatus.BAD_REQUEST, e.getMessage(), null, false, null, null));
	}

	@ExceptionHandler(value = { PasswordNotResetException.class })
	public ResponseEntity<Object> handler(PasswordNotResetException e) {
		return ResponseEntity.badRequest()
				.body(new GeneralResponse(HttpStatus.BAD_REQUEST, e.getMessage(), null, false, null, null));
	}

	@ExceptionHandler(value = { BatchNotSavedException.class })
	public ResponseEntity<Object> handler(BatchNotSavedException e) {
		return ResponseEntity.badRequest()
				.body(new GeneralResponse(HttpStatus.BAD_REQUEST, e.getMessage(), null, null, null, null));
	}

	@ExceptionHandler(value = { BatchNotUpdatedException.class })
	public ResponseEntity<Object> handler(BatchNotUpdatedException e) {
		return ResponseEntity.badRequest()
				.body(new GeneralResponse(HttpStatus.BAD_REQUEST, e.getMessage(), null, false, null, null));
	}

	@ExceptionHandler(value = { BatchNotDeletedException.class })
	public ResponseEntity<Object> handler(BatchNotDeletedException e) {
		return ResponseEntity.badRequest()
				.body(new GeneralResponse(HttpStatus.BAD_REQUEST, e.getMessage(), null, false, null, null));
	}

	@ExceptionHandler(value = { org.hibernate.exception.ConstraintViolationException.class })
	public ResponseEntity<Object> handler(org.hibernate.exception.ConstraintViolationException e) {
		return ResponseEntity.badRequest()
				.body(new GeneralResponse(HttpStatus.BAD_REQUEST,
						"Some other data depends on the data you are trying to delete, it cannot be allowed!", null,
						null, null, null));
	}

	@ExceptionHandler(value = { ConstraintViolationException.class })
	public ResponseEntity<Object> handler(ConstraintViolationException e) {
		ArrayList<String> messages = new ArrayList<>();
		Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
		for (ConstraintViolation<?> constraintViolation : constraintViolations) {
			messages.add(constraintViolation.getMessage());
		}
		return ResponseEntity.badRequest()
				.body(new GeneralResponse(HttpStatus.BAD_REQUEST, e.getMessage(), null, null, null, messages));
	}

	@ExceptionHandler(value = { DataIntegrityViolationException.class })
	public ResponseEntity<Object> handler(DataIntegrityViolationException e) {
		return ResponseEntity.badRequest().body(new GeneralResponse(HttpStatus.BAD_REQUEST,
				"User with provided data already exists!", null, null, null, null));
	}

	@ExceptionHandler(value = { HttpRequestMethodNotSupportedException.class })
	public ResponseEntity<Object> handler(HttpRequestMethodNotSupportedException e) {
		return ResponseEntity.badRequest()
				.body(new GeneralResponse(HttpStatus.BAD_REQUEST, e.getMessage(), null, null, null, null));
	}

	@ExceptionHandler(value = { MethodArgumentNotValidException.class })
	public ResponseEntity<Object> handler(MethodArgumentNotValidException e) {
		ArrayList<String> messages = new ArrayList<>();
		List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
		for (ObjectError objectError : allErrors) {
			messages.add(objectError.getDefaultMessage());
		}
		return ResponseEntity.badRequest()
				.body(new GeneralResponse(HttpStatus.BAD_REQUEST, "Validation failed!", null, null, null, messages));
	}

	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<Object> handler(Exception e) {
		return ResponseEntity.badRequest()
				.body(new GeneralResponse(HttpStatus.BAD_REQUEST, e.getMessage(), null, null, null, null));
	}
}
