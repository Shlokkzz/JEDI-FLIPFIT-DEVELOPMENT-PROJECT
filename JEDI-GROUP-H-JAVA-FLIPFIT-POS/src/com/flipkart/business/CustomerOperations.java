package com.flipkart.business;

import java.util.Scanner;

import com.flipkart.bean.*;
import com.flipkart.client.CustomerMenu;
import com.flipkart.client.MainMenu;
import com.flipkart.dao.CustomerDaoImpl;
import com.flipkart.dao.UserDaoImpl;


public class CustomerOperations {
	
	static Scanner sc=new Scanner(System.in);
	static UserOperations userService=new UserOperations();
	
	public static Boolean createCustomer(){
		System.out.println("This method is used to create a customer");
		
		System.out.println("1. Enter Username");
		String userName = sc.next();
        System.out.println("2. Enter Email");
		String email = sc.next();
        System.out.println("3. Enter Password");
        String password  = sc.next();
        
        System.out.println("4. Return to Menu");
        
        userService.createUser(userName, email, password, "customer");
        
        
		return null;
	}
	
	public static boolean updateCustomer(String userId){
		System.out.println("What do you want to update " + userId);
		
		System.out.println("Welcome Customer! Here are your options:");
        System.out.println("1. Update Username");
        System.out.println("2. Update Email");
        System.out.println("3. Update Password");
        System.out.println("4. Return to Menu");
        
        int choice = sc.nextInt();
        
        switch(choice)
        {
    	case 1:
    		System.out.println("Enter  new username");
    		String userName = sc.next();
    		
    		//pass control to userDao
    		if(userService.updateUser(userId,choice ,userName))
    		{
    			System.out.println("User updated succesfully");
    		}
    		else
    		{
    			System.out.println("Operation unsuccesful");
    		}
    
    		
    		break;
    	case 2:
    		System.out.println("Enter  new email");
    		String email = sc.next();
    		
    		//pass control to userDao
    		if(userService.updateUser(userId,choice ,email))
    		{
    			System.out.println("User updated succesfully");
    		}
    		else
    		{
    			System.out.println("Operation unsuccesful");
    		}
    	case 3:
    		System.out.println("Enter  new password");
    		String password  = sc.next();
    		
    		//pass control to userDao
    		if(userService.updateUser(userId,choice ,password))
    		{
    			System.out.println("User updated succesfully");
    		}
    		else
    		{
    			System.out.println("Operation unsuccesful");
    		}
    		
    	case 4:
    		CustomerMenu.displayCustomerMenu( userId);
    	default:
    		System.out.println("Invalid choice!");
        }
        
        
		
		return true; // fix this later 
	}
	public static boolean deleteCustomer(String userId) {
		
		userService.deleteUser(userId);
		
		
		return true;
	}
	
	//BOOKING METHODS BELOW 
	
	    
	    public static void requestBooking(Slot slot, GymCentre gym, String userId) {
	        if(CustomerDaoImpl.isSlotAvailable(slot, gym)) {
	            String bookingId = CustomerDaoImpl.doesUserhavePrevBooking(slot, userId);
	            if(!bookingId.equals("")) {
	                cancelBooking(bookingId, userId);
	            }
	            CustomerDaoImpl.addBooking(slot, gym, userId);
	            System.out.println("Booking of " + slot.getStartTime() + " at " + gym.getName() + "done!");
	        }
	        else {
	            System.out.println("The selected gym doesn't have a slot.");
	        }
	        
	    }
	    
	    public static void cancelBooking(String bookingId, String userId) {
	        BookSlot booking = new BookSlot();//get from dao
	        CustomerDaoImpl.cancelBooking(bookingId, userId);
	        System.out.println("Booking of " + booking.getSlot().getStartTime() + " at " + booking.getFlipfitGym().getName() + "cancelled.");

	    }
	    
	    public static void viewBooking(String userId) {
	        System.out.println("Bookings:");
	        List<BookSlot> bookings = CustomerDaoImpl.viewBookings(); 
	        for(BookSlot booking : bookings) {
	            System.out.println("You have a booking in " + booking.getFlipfitGym().getName() + " at time slot " + booking.getSlot().getStartTime());
	        }
	        
	    }
	



	
	
}
