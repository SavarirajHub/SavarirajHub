package com.rfpio.user.exception;

public class UserExistException extends RuntimeException{
	
	private static final long serialVersionUID = 123L;

	public UserExistException() {
		super();
	}
	public UserExistException(String message, Throwable th) {
		super(message, th);
	}
	
	public UserExistException(String message) {
		super(message);
	}
}
