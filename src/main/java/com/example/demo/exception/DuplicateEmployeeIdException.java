package com.example.demo.exception;

public class DuplicateEmployeeIdException extends RuntimeException{

	public DuplicateEmployeeIdException(String msg) {
		super(msg);
	}
}
