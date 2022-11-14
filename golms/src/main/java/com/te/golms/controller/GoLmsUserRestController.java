package com.te.golms.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.golms.entity.model.EmployeeModel;
import com.te.golms.entity.model.LoginModel;
import com.te.golms.entity.model.PasswordResetModel;
import com.te.golms.exception.PasswordNotResetException;
import com.te.golms.exception.UserAlreadyExistException;
import com.te.golms.response.GeneralResponse;
import com.te.golms.service.GoLmsUserService;
import com.te.golms.utils.jwt.JwtUtils;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/user")
@RequiredArgsConstructor
public class GoLmsUserRestController {
	private final GoLmsUserService goLmsUserService;
	private final JwtUtils jwtUtils;
	private final AuthenticationManager authenticationManager;

	@PostMapping(path = "/register")
	public ResponseEntity<GeneralResponse> register(@RequestBody EmployeeModel employeeModel) {
		EmployeeModel saveEmployee = goLmsUserService.saveEmployee(employeeModel);
		if (saveEmployee != null) {
			return ResponseEntity.ok().body(new GeneralResponse(HttpStatus.ACCEPTED, null,
					"Employee is registered, need to wait for approval!", false, null, saveEmployee));
		}
		throw new UserAlreadyExistException("User already existes with the data provided!");
	}

	@PostMapping(path = "/login")
	public ResponseEntity<GeneralResponse> login(@RequestBody LoginModel login) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(login.getEmpId(), login.getPassword()));
		String token = jwtUtils.generateToken(login.getEmpId());
		goLmsUserService.setLastLogin(login.getEmpId());
		if (goLmsUserService.checkPasswordReset(login.getEmpId())) {
			return ResponseEntity.ok().body(new GeneralResponse(HttpStatus.OK, null,
					"User " + login.getEmpId() + " is authenticated!", true, token, null));
		} else {
			return ResponseEntity.ok()
					.body(new GeneralResponse(HttpStatus.OK, null,
							"User " + login.getEmpId() + " is authenticated, but password is required to be reset!",
							false, token, null));
		}
	}

	@PostMapping(path = "/getMessage")
	public String getMessage() {
		return "Got message!";
	}

	@PostMapping(path = "/passwordReset")
	public ResponseEntity<GeneralResponse> passwordReset(@RequestBody PasswordResetModel reset) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(reset.getEmpId(), reset.getOldPassword()));
		boolean lmsUser = goLmsUserService.passwordReset(reset);
		if (lmsUser) {
			return ResponseEntity.ok()
					.body(new GeneralResponse(HttpStatus.OK, null, "Password reset is done!", true, null, null));
		}
		throw new PasswordNotResetException("Wrong Credentials Or Account is not active!");
	}

}
