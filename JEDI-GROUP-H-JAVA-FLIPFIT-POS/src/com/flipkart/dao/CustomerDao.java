package com.flipkart.dao;

import com.flipkart.bean.GymCentre;
import com.flipkart.bean.Slot;

public interface CustomerDao {

	public   void createCustomer();
	
	public   boolean updateCustomer(int id);
	
	public   boolean deleteCustomer(int id);
	
	public   void listCustomer();
	
	public   void requestBooking(Slot slot, GymCentre gym, String userId);
	
	public   void cancelBooking(String bookingId, String userId);
	
	public   void viewBooking(String userId);
}
