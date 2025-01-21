/**
 * 
 */
package com.flipkart.bean;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 
 */
public class Slot {
	
	private String slotId;
	private int seats;
	private LocalDateTime startTime;
	
	/**
	 * @return the slotId
	 */
	public String getSlotId() {
		return slotId;
	}
	/**
	 * @param slotId the slotId to set
	 */
	public void setSlotId(String slotId) {
		this.slotId = slotId;
	}
	/**
	 * @return the seats
	 */
	public int getSeats() {
		return seats;
	}
	/**
	 * @param seats the seats to set
	 */
	public void setSeats(int seats) {
		this.seats = seats;
	}
	/**
	 * @return the startTime
	 */
	public LocalDateTime getStartTime() {
		return startTime;
	}
	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}
	

}
