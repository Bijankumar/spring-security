package com.te.golms.response;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GeneralResponse {
	private HttpStatus status;
	private String error;
	private String message;
	private ZonedDateTime timeStamp = ZonedDateTime.now(ZoneId.of("Z"));
	private Boolean isPasswordReset;
	private String token;
	private Object data;

	public GeneralResponse(HttpStatus status, String error, String message, Boolean isPasswordReset, String token,
			Object data) {
		super();
		this.status = status;
		this.error = error;
		this.message = message;
		this.isPasswordReset = isPasswordReset;
		this.token = token;
		this.data = data;
	}
}
