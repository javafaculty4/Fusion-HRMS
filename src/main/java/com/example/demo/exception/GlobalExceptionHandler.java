package com.example.demo.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public String handleResourseNotFoundException(ResourceNotFoundException e) {
		return e.getMessage();
	}
	
	@ExceptionHandler(DuplicateEmployeeIdException.class)
	public String handleDuplicateEmployeeIdException(DuplicateEmployeeIdException e) {
		return e.getMessage();
	}

}
