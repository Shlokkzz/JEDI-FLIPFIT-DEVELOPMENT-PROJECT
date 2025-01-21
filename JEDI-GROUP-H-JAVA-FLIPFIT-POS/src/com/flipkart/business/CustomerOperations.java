/**
 * 
 */
package com.flipkart.business;

/**
 * 
 */
public class CustomerOperations {
	public void createCustomer(){
		System.out.println("This method is used to create a customer");
	}
	
	public boolean updateCustomer(int id){
		System.out.println("Customer is updated by id" + id);
		return true;
	}
	public boolean deleteCustomer(int id) {
		System.out.println("Customer is deleted by id" + id);
		return true;
	}
//	public void listCustomer() {
//		System.out.println("");
//	}
	
	public void requestBooking(Slot slot, Gym gym, String userId) {
		System.out.println("Customer requests booking\n");
	}
	
	public void cancelBooking(String bookingId, String userId) {
		System.out.println("Customer cancels booking\n");
	}
	
	public void viewBooking(String userId) {
		System.out.println("Bookings: \n");
	}
}
