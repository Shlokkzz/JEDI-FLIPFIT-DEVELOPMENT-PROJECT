package com.flipkart.dao;

import com.flipkart.bean.GymCentre;
import com.flipkart.bean.Slot;

public interface CustomerDao {

	public static void createCustomer(){
		System.out.println("This method is used to create a customer");
	}
	
	public static boolean updateCustomer(int id){
		System.out.println("Customer is updated by id" + id);
		return true;
	}
	public static boolean deleteCustomer(int id) {
		System.out.println("Customer is deleted by id" + id);
		return true;
	}
	public static void listCustomer() {
		System.out.println("");
	}
	
	public static void requestBooking(Slot slot, GymCentre gym, String userId) { 
		System.out.println("Customer requests booking");
	}
	
	public static void cancelBooking(String bookingId, String userId) {
		System.out.println("Customer cancels booking");
	}
	
	public static void viewBooking(String userId) { 
		System.out.println("Bookings:");
	}
}
