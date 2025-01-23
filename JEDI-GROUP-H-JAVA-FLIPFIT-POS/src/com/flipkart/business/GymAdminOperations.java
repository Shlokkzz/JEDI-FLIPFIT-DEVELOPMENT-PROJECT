package com.flipkart.business;

import java.util.*;

import com.flipkart.bean.*;

public class GymAdminOperations {
	
	public static void approveGymOwner(String userId) {
		System.out.println(userId+" gym owner registration approved.");
	}
	
	public static void approveGym(String gymId) {
		System.out.println(gymId + " gym registration approved.");
	}
	
	public static void viewGyms() {
		System.out.println("Registered Gyms are:");
		List<GymCentre> allGyms = GymRepository.getAllGyms();
		for(GymCentre gym : allGyms) {
			//printGym
		}
	}
	
	public static void viewPendingGymOwners() {
		System.out.println("Pending Gym owners:");
		System.out.println("No pending gyms owners.");//print all gym owners
		
	}
	
	public static void viewPendingGyms() {
		System.out.println("Pending Gyms:");
		System.out.println("No pending gyms.");//print all gyms
	}
	
}
