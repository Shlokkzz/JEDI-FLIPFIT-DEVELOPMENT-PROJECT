package com.flipkart.bean;

// Represents a role entity.
public class Role {
    private String roleID;
    private String roleType;

    /* Constructor to initialize a Role object.
     Parameters:
     roleID: ID of the role.
     roleType: Type of the role.
     */
    public Role(String roleID, String roleType) {
        this.roleID = roleID;
        this.roleType = roleType;
    }

    // Getter for roleID.
    public String getRoleID() {
        return roleID;
    }

    // Setter for roleID.
    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    // Getter for roleType.
    public String getRoleType() {
        return roleType;
    }

    // Setter for roleType.
    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }
}
