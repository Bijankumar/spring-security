package com.te.spring.boot.custom.exception;

public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException(String args) {
		super(args);
	}
}
