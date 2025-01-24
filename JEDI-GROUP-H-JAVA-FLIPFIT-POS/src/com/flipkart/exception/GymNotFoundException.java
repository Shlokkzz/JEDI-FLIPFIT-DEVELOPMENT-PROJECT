package com.flipkart.exception;

/**
 * Exception thrown when a gym center is not found.
 */
public class GymNotFoundException extends Exception {

    /**
     * Constructs a new GymNotFoundException with the specified gymId.
     *
     * @param gymId the ID of the gym center that was not found.
     */
    public GymNotFoundException(int gymId) {
        super("Gym Centre " + gymId + " not found!");
    }
}
