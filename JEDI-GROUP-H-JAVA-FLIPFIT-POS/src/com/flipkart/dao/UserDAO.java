package com.flipkart.dao;

import com.flipkart.bean.Customer;
import com.flipkart.bean.GymOwner;
import com.flipkart.bean.User;

public interface UserDAO {
    public User validateUser(String username, String password);
    public boolean registerGymOwner(GymOwner gymOwner);
    public boolean registerCustomer(Customer customer);
    public boolean updateUser(User user);
}
