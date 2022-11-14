package com.te.learnspringsecurity.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.learnspringsecurity.entity.dto.LoginDto;
import com.te.learnspringsecurity.jwt.utils.JwtUtils;
import com.te.learnspringsecurity.response.GeneralResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "/api")
@RequiredArgsConstructor
public class AppController {

	private final JwtUtils jwtUtils;
	private final AuthenticationManager authenticationManager;

	/* PUBLIC API */
	@GetMapping(path = "")
	public String publicInfo() {
		return "Anyone can access!";
	}

	/* PUBLIC API */
	@PostMapping(path = "/login")
	public ResponseEntity<GeneralResponse> login(@RequestBody LoginDto login) {
		log.error("In controller method login(). Username passed {} and password passed {}", login.getUsername(),
				login.getPassword());
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
		String token = jwtUtils.generateToken(login.getUsername());
		return ResponseEntity.ok().body(new GeneralResponse(token, HttpStatus.OK, null, null, "Login!"));
	}

//	/* ADMIN */
//	@PostMapping(path = "/mentor")
//	public ResponseEntity<GeneralResponse> registerMentor(@RequestBody MentorDto mentorDto) {
//		// TODO: Calling service method to save mentor data!
//		return ResponseEntity.ok().body(new GeneralResponse(null, HttpStatus.OK, null, null, "Only for Admin!"));
//	}
//
//	/* EMPLOYEE */
//	@PostMapping(path = "/mentor")
//	public ResponseEntity<GeneralResponse> registerMentor(@RequestBody EmployeeDto mentorDto) {
//		// TODO: Calling service method to save employee data!
//		return ResponseEntity.ok().body(new GeneralResponse(null, HttpStatus.OK, null, null, "Only for Employee!"));
//	}

	/* ADMIN */
	@GetMapping(path = "/admin/{adminId}")
	public ResponseEntity<GeneralResponse> getAdminInfo(@PathVariable String adminId) {
		return ResponseEntity.ok().body(new GeneralResponse(null, HttpStatus.OK, null, null, "Only for Admin!"));
	}

	/* ADMIN + MENTOR */
	@GetMapping(path = "/mentor/{mentorId}")
	public ResponseEntity<GeneralResponse> getMentorInfo(@PathVariable String mentorId) {
		return ResponseEntity.ok().body(new GeneralResponse(null, HttpStatus.OK, null, null, "For Admin and Mentor!"));
	}

	/* ADMIN + MENTOR + EMPLOYEE */
	@GetMapping(path = "/employee/{employeeId}")
	public ResponseEntity<GeneralResponse> getEmployeeInfo(@PathVariable String employeeId) {
		return ResponseEntity.ok()
				.body(new GeneralResponse(null, HttpStatus.OK, null, null, "For Admin, Mentor and Employee!"));
	}

	/* ADMIN */
	@GetMapping(path = "/admin")
	public ResponseEntity<GeneralResponse> onlyAccessibleToAdmin() {
		return ResponseEntity.ok().body(new GeneralResponse(null, HttpStatus.OK, null, null, "Only for Admin!"));
	}

	/* MENTOR */
	@GetMapping(path = "/mentor")
	public ResponseEntity<GeneralResponse> onlyAccessibleToMentor() {
		return ResponseEntity.ok().body(new GeneralResponse(null, HttpStatus.OK, null, null, "Only for Mentor!"));
	}

	/* EMPLOYEE */
	@GetMapping(path = "/employee")
	public ResponseEntity<GeneralResponse> onlyAccessibleToEmployee() {
		return ResponseEntity.ok().body(new GeneralResponse(null, HttpStatus.OK, null, null, "Only for Employee!"));
	}
}
