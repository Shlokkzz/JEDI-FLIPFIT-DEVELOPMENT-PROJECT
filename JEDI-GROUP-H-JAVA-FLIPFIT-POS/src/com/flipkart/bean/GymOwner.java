package com.flipkart.bean;

import java.util.ArrayList;
import java.util.List;

// Represents a gym owner who is a user of the system.
public class GymOwner extends User {
    private String name;
    private String email;
    private String contactNo;
    private int age;
    public List<GymCenter> gymCenters;

    /* Constructor to initialize a GymOwner object.
     Parameters:
     username: Username of the gym owner (inherited from User class).
     name: Name of the gym owner.
     email: Email address of the gym owner.
     contactNo: Contact number of the gym owner.
     age: Age of the gym owner.
     password: Password of the gym owner (inherited from User class).
     id: ID of the gym owner (inherited from User class).
     roleId: Role ID of the gym owner (inherited from User class).
     */

    public GymOwner(String username, String name, String email, String contactNo, int age, String password, String id, String roleId) {
        super(username, password, id, roleId);  // Call to the superclass (User) constructor.
        this.name = name;
        this.email = email;
        this.contactNo = contactNo;
        this.age = age;
        this.gymCenters = new ArrayList<>();   // Initialize the gymCenters list.
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

    // Getter for contactNo.
    public String getContactNo() {
        return contactNo;
    }

    // Setter for contactNo.
    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    // Getter for age.
    public int getAge() {
        return age;
    }

    // Setter for age.
    public void setAge(int age) {
        this.age = age;
    }

    // Getter for gymCenters.
    public List<GymCenter> getGymCenters() {
        return gymCenters;
    }

    // Setter for gymCenters.
    public void setGymCenters(List<GymCenter> gymCenters) {
        this.gymCenters = gymCenters;
    }

    /* Method to search for a gym center owned by the gym owner based on name and city.
     Returns the GymCenter object if found, otherwise returns null.
     */
    public GymCenter searchGC(String name, String city) {
        for (GymCenter gc : gymCenters) {
            if (gc.getGymName().equals(name) && gc.getCity().equals(city)) {
                return gc;
            }
        }
        return null;
    }
}
