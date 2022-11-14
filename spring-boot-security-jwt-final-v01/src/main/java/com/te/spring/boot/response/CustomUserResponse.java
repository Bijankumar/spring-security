package com.te.spring.boot.response;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomUserResponse {
	private HttpStatus httpStatus;
	private String token;
	private String message;
	private String errMessage;
}
