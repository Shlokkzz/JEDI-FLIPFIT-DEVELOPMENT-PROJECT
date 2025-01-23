/**
 * 
 */
package com.flipkart.bean;

import java.util.List;

/**
 * 
 */
public class GymRepository {
	
	private static List<GymCentre> allGyms;

	/**
	 * @return the allGyms
	 */
	public static List<GymCentre> getAllGyms() {
		return allGyms;
	}

	/**
	 * @param allGyms the allGyms to set
	 */
	public static void setAllGyms(List<GymCentre> allGyms) {
		GymRepository.allGyms = allGyms;
	}
	

}
