/**
 * 
 */
package com.flipkart.bean;

import java.util.List;

/**
 * 
 */
public class GymOwner extends User {
	
	
	private List<GymCentre> gymsOwned;
	

	/**
	 * @return the gymsOwned
	 */
	public List<GymCentre> getGymsOwned() {
		return gymsOwned;
	}

	/**
	 * @param gymsOwned the gymsOwned to set
	 */
	public void setGymsOwned(List<GymCentre> gymsOwned) {
		this.gymsOwned = gymsOwned;
	}
	
}
