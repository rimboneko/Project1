package com.project1.exceptions;

public class UsernameAlreadyExistsException extends Exception {

	
	private static final long serialVersionUID = 1L;
	
	public UsernameAlreadyExistsException(){
		super("This username already exists");
	}

}
