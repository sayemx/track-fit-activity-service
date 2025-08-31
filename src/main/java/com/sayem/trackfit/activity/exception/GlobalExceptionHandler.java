package com.sayem.trackfit.activity.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sayem.trackfit.activity.dto.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ActivityNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleUserNotFoundException(ActivityNotFoundException ex) {
		
		ErrorResponse errorResponse = new ErrorResponse(
				LocalDateTime.now(), 
				HttpStatus.BAD_REQUEST,
				"Bad Request",
				"Activity not found : " + ex.getMessage()
				);
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(errorResponse);
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex) {
		
		ErrorResponse errorResponse = new ErrorResponse(
				LocalDateTime.now(), 
				HttpStatus.INTERNAL_SERVER_ERROR,
				"Something went wrong",
				"Error occured : " + ex.getMessage()
				);
		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(errorResponse);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex) {
		
		ErrorResponse errorResponse = new ErrorResponse(
				LocalDateTime.now(), 
				HttpStatus.BAD_REQUEST,
				"Bad Request",
				"User not found : " + ex.getMessage()
				);
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(errorResponse);
	}
	
	@ExceptionHandler(ExcerciseAlreadyExist.class)
	public ResponseEntity<ErrorResponse> handleExcerciseAlreadyExistException(ExcerciseAlreadyExist ex) {
		
		ErrorResponse errorResponse = new ErrorResponse(
				LocalDateTime.now(), 
				HttpStatus.BAD_REQUEST,
				"Bad Request",
				"Excercise is already exist : " + ex.getMessage()
				);
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(errorResponse);
	}
}
