package com.flipkart.business;
import java.util.*;

import com.flipkart.bean.*;
import com.flipkart.dao.AdminDAOImpl;
import com.flipkart.dao.UserDAOImpl;

import com.flipkart.utils.sharedState;


public class AdminService implements AdminServiceInterface {
    public AdminService(){
        initializeAdmin();
    }
    UserService userService = new UserService();
    UserDAOImpl userDAO = new UserDAOImpl();
    AdminDAOImpl adminDAO = new AdminDAOImpl();

    public static Map<String, GymCenter> gymCenters = new HashMap<>();
    public static Map<String, GymCenter> pendingCenters = new HashMap<>();
    private void initializeAdmin() {
        Role role = new Role("A","ADMIN");
        Admin admin = new Admin("bean", "BeanAdmin", "bean@gmail.com", "1234567890", 0, "bean@1234", "00", role.getRoleID());
        User user = new User(admin.getUsername(), admin.getPassword(), admin.getUserid(), admin.getRoleId());
        userService.addUser(user);
        sharedState.incrementCntUsers();
        User u = userDAO.validateUser(user.getUsername(), user.getPassword());
        if (u == null) {
            if (userDAO.addUser(user)) {
                System.out.println("User added successfully");
            }
        }
    }

    @Override
    public Object approveGymCenterById(String gymID) {
        boolean ap = adminDAO.approveGymCenter(gymID);
        if(ap)
            return "Gym Center approved";
        else
            return "Gym Center not approved";
    }
    @Override
    public Object approveGymOwnerById(String ID) {
        boolean ap = adminDAO.approveGymOwner(ID);
        if(ap)
            return "Gym Owner approved";
        else
            return "Gym Owner not approved";
    }

    @Override
    public Object approveAllGymCenters() {   //done
    	boolean ap = adminDAO.approveAllGymCenter();
        if(ap)
//            System.out.println("All Gym Center approved");
            return "All Gym Centers approved";
        else
//            System.out.println("All Gym Center not approved");
            return "All Gym Centers not approved";
    }

    @Override
    public Object approveAllGymOwners() {
        boolean ap = adminDAO.approveAllGymOwner();
        if(ap)
            return "All GymOwner approved";
        else
            return "All GymOwner not approved";
    }

    @Override
    public Object listPendingGymCenters() {  // view
        List<GymCenter> pendingGC = adminDAO.getPendingGymCenters();
        System.out.println("Listing all pending gym owners");
        for (GymCenter gymCenter : pendingGC) {
            System.out.println("Gym ID: " + gymCenter.getGymID()+" Gym Name: "+ gymCenter.getGymName());
        }

        return pendingGC;
    }


    @Override
    public Object listPendingGymOwners() {
        List<GymOwner> pendingGO = adminDAO.getPendingGymOwners();
        System.out.println("Listing all pending gym owners");
        for (GymOwner gymOwner : pendingGO) {
            System.out.println("Owner ID: " + gymOwner.getUserid()+" Owner Name: "+gymOwner.getName());
        }

        return pendingGO;
    }

    @Override
    public Object listGymCenters() {    // view
        List<GymCenter> gymCenters = adminDAO.getAllGymCenters();
        System.out.println("Listing all gym centers");
        for (GymCenter gymCenter : gymCenters) {
            System.out.println("Gym ID: " + gymCenter.getGymID()+" Gym Name: "+ gymCenter.getGymName());
        }
        return gymCenters;
    }

    @Override
    public Object listGymOwners() {
        System.out.println("Listing all gym owners");
        List<GymOwner> gymOwners = adminDAO.getAllGymOwners();
        for (GymOwner gymOwner : gymOwners) {
            System.out.println("Owner ID: " + gymOwner.getUserid()+" Owner Name: "+gymOwner.getName());
        }
        return gymOwners;
    }

    @Override
    public Object listUsers() {
        System.out.println("Listing all users");
         List<User> allUsers = adminDAO.getAllUser();
         System.out.println(allUsers.size());
         for (User user : allUsers) {
             System.out.println("User ID: " + user.getUserid()+" User Name: "+ user.getUsername());
         }
        return allUsers;
    }
}


