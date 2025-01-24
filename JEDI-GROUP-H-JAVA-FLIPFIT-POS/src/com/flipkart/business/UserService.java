// Package declaration
        package com.flipkart.business;

// Import necessary classes
import com.flipkart.bean.Customer;
import com.flipkart.bean.GymOwner;
import com.flipkart.bean.User;
import com.flipkart.dao.UserDAO;
import com.flipkart.dao.UserDAOImpl;
import com.flipkart.exception.InvalidLogin;

import java.util.HashMap;

/**
 * This class implements the UserServiceInterface and provides methods
 * to manage user-related operations such as adding a user, logging in,
 * validating passwords, confirming password changes, and registering
 * gym owners and customers.
 */
public class UserService implements UserServiceInterface {
    // HashMap to store users with their usernames as keys
    static HashMap<String, User> UsersMap = new HashMap<>();
    // UserDAO instance to interact with the data access layer
    private UserDAOImpl userDAOImpl = new UserDAOImpl();
    private UserDAO userDAO = new UserDAOImpl();

    /**
     * Adds a user to the UsersMap
     */
    @Override
    public void addUser(User user) {
        UsersMap.put(user.getUsername(), user);
    }

    /**
     * Logs in a user by validating the username and password.
     */
    @Override
    public User login(String username, String password) throws InvalidLogin {
        // Fetch the user from user DB based on the provided username
        return userDAOImpl.validateUser(username, password);
    }

    /**
     * Validates if the given old password matches the user's current password.
     */
    @Override
    public boolean validatePassword(User user, String oldPassword) {
        return user.getPassword().equals(oldPassword);
    }

    /**
     * Confirms and updates the user's password if the new password and confirm password match.
     */
    @Override
    public void confirmPassword(User user, String newPassword, String confirmPassword) {
        if (newPassword.equals(confirmPassword)) {
            user.setPassword(newPassword);
            if(userDAO.updateUser(user)){
                System.out.println("Password Changed Successfully");
            }
        } else {
            System.out.println("Password did not match");
        }
    }

    /**
     * Registers a gym owner
     */
    @Override
    public boolean registerGymOwner(GymOwner gymOwner) {
        return userDAO.registerGymOwner(gymOwner);
    }

    /**
     * Registers a customer
     */
    @Override
    public boolean registerCustomer(Customer customer) {
        return userDAO.registerCustomer(customer);
    }
}