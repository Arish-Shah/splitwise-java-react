package com.assignment.splitwise.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.assignment.splitwise.dto.ErrorResponse;
import com.assignment.splitwise.exception.AuthException;
import com.assignment.splitwise.exception.SplitwiseException;

@ControllerAdvice
public class GlobalExceptionHandler {

	Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(SplitwiseException.class)
	public ResponseEntity<ErrorResponse> handleSplitwiseException(SplitwiseException exception) {
		ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException exception) {
		String message = exception.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
		ErrorResponse errorResponse = new ErrorResponse(message);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(AuthException.class)
	public ResponseEntity<ErrorResponse> handleSplitwiseException(AuthException exception) {
		ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException() {
		ErrorResponse errorResponse = new ErrorResponse("Something went wrong");
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
