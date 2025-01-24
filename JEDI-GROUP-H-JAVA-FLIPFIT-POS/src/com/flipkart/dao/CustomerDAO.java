package com.flipkart.dao;

import com.flipkart.bean.Booking;
import com.flipkart.bean.Customer;

import java.util.List;

public interface CustomerDAO {

//    public void fetchBookedSlots(String customerId);
//    public void cancelBooking(String GymId ,String slotId, String userId, String date);
//    public void addBooking(Booking booking);
    public boolean updateProfile(Customer customer);
    public boolean addBooking(Booking booking);
    public List<Booking> viewBookings(String userId);
}
