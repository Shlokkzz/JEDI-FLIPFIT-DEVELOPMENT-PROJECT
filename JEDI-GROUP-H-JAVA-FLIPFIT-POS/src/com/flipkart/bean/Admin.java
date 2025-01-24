package com.flipkart.bean;
// Define a class named Admin that inherits from the User class.
public class Admin extends User {
    // Constructor for creating an Admin object.
    // Parameters:
    //   username: The username of the admin.
    //   name: The full name of the admin.
    //   email: The email address of the admin.
    //   contactNo: The contact number of the admin.
    //   age: The age of the admin.
    //   password: The password associated with the admin account.
    //   id: The unique identifier for the admin.
    //   roleId: The role ID indicating the admin's role in the system.
    public Admin(String username, String name, String email, String contactNo, int age, String password, String id, String roleId) {
        // Call the constructor of the superclass (User) with necessary parameters.
        super(username, password, id, roleId);
    }
}
