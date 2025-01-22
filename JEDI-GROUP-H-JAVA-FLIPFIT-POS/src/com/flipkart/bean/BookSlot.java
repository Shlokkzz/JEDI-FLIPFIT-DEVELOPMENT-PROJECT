/**
 * 
 */
package com.flipkart.bean;

/**
 * 
 */
public class BookSlot {
	
	private String bookingId, paymentId, userId, status;
	private Slot slot;
	private GymCentre flipfitGym;
	
	/**
	 * @return the bookingId
	 */
	public String getBookingId() {
		return bookingId;
	}
	/**
	 * @param bookingId the bookingId to set
	 */
	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}
	/**
	 * @return the paymentId
	 */
	public String getPaymentId() {
		return paymentId;
	}
	/**
	 * @param paymentId the paymentId to set
	 */
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the slot
	 */
	public Slot getSlot() {
		return slot;
	}
	/**
	 * @param slot the slot to set
	 */
	public void setSlot(Slot slot) {
		this.slot = slot;
	}
	/**
	 * @return the flipfitGym
	 */
	public GymCentre getFlipfitGym() {
		return flipfitGym;
	}
	/**
	 * @param flipfitGym the flipfitGym to set
	 */
	public void setFlipfitGym(GymCentre flipfitGym) {
		this.flipfitGym = flipfitGym;
	}
	
	

}
