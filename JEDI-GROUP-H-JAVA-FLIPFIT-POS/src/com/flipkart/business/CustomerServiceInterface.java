package com.flipkart.business;

import com.flipkart.bean.Booking;
import com.flipkart.bean.Customer;
import com.flipkart.bean.User;

import java.util.List;

/**
 * Interface defining the operations related to customer services.
 * This includes creating a customer, showing profile details,
 * viewing and canceling bookings, and editing profile information.
 */
public interface CustomerServiceInterface {

    /**
     * Creates a new customer with the provided details.
     *
     * @param username the username of the customer
     * @param name the name of the customer
     * @param email the email address of the customer
     * @param phone the phone number of the customer
     * @param age the age of the customer
     * @param password the password for the customer account
     */
    public void createCustomer(String username, String name, String email, String phone, int age, String password);

    /**
     * Displays the profile information of a customer by their ID.
     *
     * @param customer the object of class Customer
     */
    public void showProfile(Customer customer);

    /**
     * Displays the bookings of a customer by their customer ID.
     *
     * @param userId the ID of the user
     */
    public List<Booking> viewBookings(String userId);

    /**
     * Cancels the bookings of a customer by their user ID.
     *
     * @param userId the ID of the user
//     */
//    void cancelbookings(String userId);

    /**
     * Edits the profile information of a customer.
     *
     * @param customer the Customer object containing updated profile information
//     */
    public boolean editProfile(Customer customer);

}
