package com.te.spring.security.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.te.spring.security.beans.CustomUser;
import com.te.spring.security.model.CustomUserModel;
import com.te.spring.security.service.CustomUserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(path = "/api")
@RequiredArgsConstructor
public class CustomUserController {

	private final CustomUserService customUserService;
	private final PasswordEncoder passwordEncoder;

	@PostMapping(path = "/user/register")
	public ResponseEntity<CustomUser> register(@RequestBody CustomUserModel customUserModel) {
		System.out.println("In controller register()!");
		System.out.println(customUserModel);
		CustomUser customUser = new CustomUser();
		customUser.setEmail(customUserModel.getEmail());
		customUser.setFirstName(customUserModel.getFirstName());
		customUser.setLastName(customUserModel.getLastName());
		// customUser.setPassword(passwordEncoder.encode(customUserModel.getPassword()));
		customUser.setPassword(customUserModel.getPassword());
		customUserService.register(customUser);
		return ResponseEntity.created(null).body(customUser);
	}

	@GetMapping(path = "/user/list")
	public ResponseEntity<List<CustomUser>> getUsers() {
		return ResponseEntity.ok().body(customUserService.getUsers());
	}
}
