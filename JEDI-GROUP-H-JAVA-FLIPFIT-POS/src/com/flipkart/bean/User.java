package com.flipkart.bean;

// Represents a user in the system.
public class User {
    private String username;
    private String password;
    private String userid;
    private String roleId;

    /* Constructor to initialize a User object.
     Parameters:
     username: Username of the user.
     password: Password of the user.
     id: ID of the user.
     roleId: Role ID of the user.
     */
    public User(String username, String password, String id, String roleId) {
        this.username = username;
        this.password = password;
        this.userid = id;
        this.roleId = roleId;
    }

    // Getter for userid.
    public String getUserid() {
        return userid;
    }

    // Setter for userid.
    public void setUserid(String userid) {
        this.userid = userid;
    }

    // Getter for roleId.
    public String getRoleId() {
        return roleId;
    }

    // Setter for roleId.
    public void setRole(String roleId) {
        this.roleId = roleId;
    }

    // Getter for username.
    public String getUsername() {
        return username;
    }

    // Setter for username.
    public void setUsername(String username) {
        this.username = username;
    }

    // Getter for password.
    public String getPassword() {
        return password;
    }

    // Setter for password.
    public void setPassword(String password) {
        this.password = password;
    }
}
