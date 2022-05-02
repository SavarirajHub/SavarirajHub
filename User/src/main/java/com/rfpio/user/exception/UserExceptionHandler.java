package com.rfpio.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.rfpio.user.model.ErrorResponse;

@ControllerAdvice
public class UserExceptionHandler {
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorResponse> userNotFoundExceptionHandler(UserNotFoundException userException) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND.value())
				.body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), userException.getLocalizedMessage()));
	}
	
	@ExceptionHandler(UserExistException.class)
	public ResponseEntity<ErrorResponse> userExistExceptionHandler(UserExistException userException) {
		return ResponseEntity.status(HttpStatus.CONFLICT.value())
				.body(new ErrorResponse(HttpStatus.CONFLICT.value(), userException.getLocalizedMessage()));
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> globalExceptionHandler(Exception exception) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage()));
	}

}
