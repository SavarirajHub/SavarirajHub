package com.rfpio.user.exception;

public class UserNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public UserNotFoundException() {
		super();
	}
	public UserNotFoundException(String message, Throwable th) {
		super(message, th);
	}
	
	public UserNotFoundException(String message) {
		super(message);
	}
	
}
