package com.example.demo.exception;

public class ResourceNotFoundException extends RuntimeException{
	
	public ResourceNotFoundException(String resourseName, String fieldName, String fieldValue) {
		super(String.format("%s Not Found with %s : %s",resourseName,fieldName,fieldValue));
	}

}
