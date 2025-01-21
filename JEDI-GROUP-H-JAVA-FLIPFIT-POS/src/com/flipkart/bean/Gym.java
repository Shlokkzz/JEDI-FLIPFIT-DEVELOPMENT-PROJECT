/**
 * 
 */
package com.flipkart.bean;

import java.util.List;

/**
 * 
 */
public class Gym {
	
	private String gymId, name, location;
	private List<Slot> totalSlots;
	
	/**
	 * @return the gymId
	 */
	public String getGymId() {
		return gymId;
	}
	/**
	 * @param gymId the gymId to set
	 */
	public void setGymId(String gymId) {
		this.gymId = gymId;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * @return the totalSlots
	 */
	public List<Slot> getTotalSlots() {
		return totalSlots;
	}
	/**
	 * @param totalSlots the totalSlots to set
	 */
	public void setTotalSlots(List<Slot> totalSlots) {
		this.totalSlots = totalSlots;
	}
	
	
};

