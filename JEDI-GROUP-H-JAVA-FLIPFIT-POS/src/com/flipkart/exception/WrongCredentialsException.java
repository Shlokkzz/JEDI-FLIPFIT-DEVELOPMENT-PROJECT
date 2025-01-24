package com.flipkart.exception;

/**
 * Exception thrown when invalid credentials are provided.
 */
public class WrongCredentialsException extends Exception {

	/**
	 * Constructs a new WrongCredentialsException with a default message.
	 */
	public WrongCredentialsException() {
		super("Invalid credentials!"); 
	}
}
