package com.flipkart.exception;

/**
 * Exception thrown when login fails due to incorrect credentials.
 */
public class LoginFailedException extends RuntimeException {

    /**
     * Constructs a new LoginFailedException with the specified detail message.
     *
     * @param message the detail message that describes the login failure.
     */
    public LoginFailedException(String message) {
        super(message);
        System.out.println("Unable to login, Check your username and password");
    }
}
