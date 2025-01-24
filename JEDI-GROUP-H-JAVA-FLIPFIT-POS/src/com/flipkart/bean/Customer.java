
package com.flipkart.bean;

import java.util.ArrayList;
import java.util.List;

// Represents a customer who is a user of the system.
public class Customer extends User {
    private String name;                // Name of the customer.
    private String email;               // Email address of the customer.
    private String phone;               // Phone number of the customer.
    private int age;                    // Age of the customer.
    private List<pair<Booking, Boolean>> bookings;  // List of bookings made by the customer.

    /* Constructor to initialize a Customer object.
    Parameters:
     username: Username of the customer (inherited from User class).
     name: Name of the customer.
     email: Email address of the customer.
     contactNo: Phone number of the customer.
     age: Age of the customer.
     password: Password of the customer (inherited from User class).
     ID: ID of the customer (inherited from User class).
     roleID: Role ID of the customer (inherited from User class).
     bookings: List of bookings made by the customer.
     */
    public Customer(String username, String name, String email, String contactNo, int age, String password, String ID, String roleID, List<Booking> bookings) {
        super(username, password, ID, roleID);  // Call to the superclass (User) constructor.
        this.name = name;
        this.email = email;
        this.phone = contactNo;
        this.age = age;
        this.bookings = new ArrayList<>();  // Initialize the bookings list.
    }

    // Getter for bookings.
    public List<pair<Booking, Boolean>> getBookings() {
        return bookings;
    }

    // Setter for bookings.
    public void setBookings(List<pair<Booking, Boolean>> bookings) {
        this.bookings = bookings;
    }

    // Getter for name.
    public String getName() {
        return name;
    }

    // Setter for name.
    public void setName(String name) {
        this.name = name;
    }

    // Getter for email.
    public String getEmail() {
        return email;
    }

    // Setter for email.
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter for phone.
    public String getPhone() {
        return phone;
    }

    // Setter for phone.
    public void setPhone(String phone) {
        this.phone = phone;
    }

    // Getter for age.
    public int getAge() {
        return age;
    }

    // Setter for age.
    public void setAge(int age) {
        this.age = age;
    }
}

