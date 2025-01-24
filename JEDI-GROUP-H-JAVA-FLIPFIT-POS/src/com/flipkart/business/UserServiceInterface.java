// Package declaration
package com.flipkart.business;

// Import necessary classes
import com.flipkart.bean.Customer;
import com.flipkart.bean.GymOwner;
import com.flipkart.bean.User;
import com.flipkart.exception.InvalidLogin;

/**
 This interface defines the operations related to user management.
 This includes adding users, user login, password validation, password confirmation,
 and registration of gym owners and customers.
 */
public interface UserServiceInterface {

    /**
     * Adds a new user
     */
    public void addUser(User user);

    /**
     * Authenticates a user with the given username and password.
     */
    public User login(String username, String password) throws InvalidLogin;

    boolean validatePassword(User user, String oldPassword);

    void confirmPassword(User user, String newPassword, String confirmPassword);

    boolean registerGymOwner(GymOwner gymOwner);

    boolean registerCustomer(Customer customer);


/**
 Validates the user's old password
 */
}
