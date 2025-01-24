// Package declaration
package com.flipkart.business;

/**
 * This interface defines the operations related to forgot password functionality.
 * This includes checking if a user exists and resetting the password.
 */
public interface ForgotPasswordServiceInterface {

    /**
     * Checks if a user with the given username exists.
     */
    public boolean isUser(String username);

    /**
     * Resets the password for the user with the given username.
     */
    public void resetPass(String username, String newPass);
}
