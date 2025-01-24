package com.flipkart.dao;

import java.util.List;

import com.flipkart.bean.BookSlot;
import com.flipkart.bean.Slot;


public interface BookingDao {
	//create delete
	public Boolean deleteBooking(String bookingId);
	
	public Boolean createBooking(String bookingId, String userId, String slotId, String paymentId, String status);
	
	public BookSlot getBookingById(String bookingid);
	
	public List<BookSlot> getBookingsforCustomer(String customerId);
	
	public String doesUserHavePrevBooking(Slot slot,String userId);
	
	
	
	
	
}
