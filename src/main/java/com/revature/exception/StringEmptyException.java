package com.revature.exception;

public class StringEmptyException extends Exception {

	private static final long serialVersionUID = 2468485245934337108L;
	
	public StringEmptyException(String message) {
		super(message);
	}
}
