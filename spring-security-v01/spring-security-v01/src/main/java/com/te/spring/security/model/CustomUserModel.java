package com.te.spring.security.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomUserModel {
	private String email;
	private String firstName;
	private String lastName;
	private String password;
	private String matchingPassword;
}
