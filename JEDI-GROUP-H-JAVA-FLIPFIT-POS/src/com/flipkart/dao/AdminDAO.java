package com.flipkart.dao;

import java.util.List;

import com.flipkart.bean.GymCenter;
import com.flipkart.bean.GymOwner;
import com.flipkart.bean.User;

public interface AdminDAO {
    public List<GymOwner> getAllGymOwners();

    public List<GymCenter> getAllGymCenters();

    public List<GymOwner> getPendingGymOwners();

    public List<GymCenter> getPendingGymRequests();

    public boolean approveGymCenter(String gymId);

    public boolean approveAllGymCenter();

    public boolean approveGymOwner(String userID);

    public boolean approveAllGymOwner();

    public List<User> getAllUser();
}
