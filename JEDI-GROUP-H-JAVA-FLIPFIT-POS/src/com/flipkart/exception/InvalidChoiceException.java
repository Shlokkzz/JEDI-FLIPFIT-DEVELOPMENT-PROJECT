package com.flipkart.exception;

import java.io.InvalidObjectException;

/**
 * Exception thrown when an invalid choice is encountered.
 */
public class InvalidChoiceException extends InvalidObjectException {

    /**
     * Constructs a new InvalidChoiceException with the specified message.
     *
     * @param message the detail message that describes the invalid choice.
     */
    public InvalidChoiceException(String message) {
        super(message);
        System.out.println("Please enter valid choice"); 
    }
}
