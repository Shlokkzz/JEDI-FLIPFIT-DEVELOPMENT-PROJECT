/**
 * 
 */
package com.flipkart.bean;

import java.util.List;

/**
 * 
 */
public class GymOwner extends User {
	
	
	private List<Gym> gymsOwned;
	

	/**
	 * @return the gymsOwned
	 */
	public List<Gym> getGymsOwned() {
		return gymsOwned;
	}

	/**
	 * @param gymsOwned the gymsOwned to set
	 */
	public void setGymsOwned(List<Gym> gymsOwned) {
		this.gymsOwned = gymsOwned;
	}
	
}
