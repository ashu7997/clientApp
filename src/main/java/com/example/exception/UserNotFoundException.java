package com.example.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


public class UserNotFoundException extends RuntimeException{
	private String message;
     
     
	public UserNotFoundException(String message) {
		super( "user not found");
		this.message = message;
	}
	

}
