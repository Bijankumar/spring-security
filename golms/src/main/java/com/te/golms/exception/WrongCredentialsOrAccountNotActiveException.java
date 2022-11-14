package com.te.golms.exception;

public class WrongCredentialsOrAccountNotActiveException extends RuntimeException {
	public WrongCredentialsOrAccountNotActiveException(String message) {
		super(message);
	}
}
