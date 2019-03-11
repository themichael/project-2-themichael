package com.revature.exception;

public class LoginFailedException extends Exception {

	private static final long serialVersionUID = -8630933208758024476L;

	public LoginFailedException(String message) {
		super(message);
	}
}
