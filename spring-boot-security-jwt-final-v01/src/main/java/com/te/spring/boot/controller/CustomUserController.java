package com.te.spring.boot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.spring.boot.beans.CustomUser;
import com.te.spring.boot.request.CustomUserRequest;
import com.te.spring.boot.response.CustomUserResponse;
import com.te.spring.boot.service.CustomUserService;
import com.te.spring.boot.util.JwtUtil;

import lombok.RequiredArgsConstructor;

/*
 * CustomUserController is the controller class for the CustomUser.
 * This class will have the controller methods for CustomUser.
 * */
@RestController
@RequestMapping(path = "/user")
@RequiredArgsConstructor
public class CustomUserController {
	private final CustomUserService customUserService;
	private final JwtUtil jwtUtil;
	private final AuthenticationManager authenticationManager;

	/* 1. This method saves the user data in the database! */
	@PostMapping(path = "/save")
	public ResponseEntity<CustomUserResponse> saveUser(@RequestBody CustomUser customUser) {
		String savedUserUsername = customUserService.saveUser(customUser);
		System.out.println("==> Username: " + savedUserUsername);
		return ResponseEntity.ok().body(new CustomUserResponse(HttpStatus.ACCEPTED, null,
				"User with username " + savedUserUsername + " was saved!", null));
	}

	/* 2. Validate user and generate token! */
	@PostMapping(path = "/login")
	public ResponseEntity<CustomUserResponse> login(@RequestBody CustomUserRequest customUserRequest) {
		
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(customUserRequest.getUsername(), customUserRequest.getPassword()));
		// 1. Generate token
		String token = jwtUtil.generateToken(customUserRequest.getUsername());
		return ResponseEntity.accepted().body(
				new CustomUserResponse(HttpStatus.ACCEPTED, token, "Login sucessfull and token generated!", null));
	}
}
