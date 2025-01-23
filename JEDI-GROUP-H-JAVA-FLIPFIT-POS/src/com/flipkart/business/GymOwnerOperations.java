package com.flipkart.business;

import com.flipkart.bean.*;

public class GymOwnerOperations {
	public static void addGym(GymCentre gym) {
		System.out.println(gym.getName() + " added successfully");
	}
	
	public static void modifySlot(Slot slot, String gymId) {
		System.out.println("Slot modify successfully!");
	}
	
	public static void deleteSlot(Slot slot, String gymId) {
		System.out.println("Slot deleted successfully!");	
	}
	
	public static void addSlot(Slot slot, String gymId) {
		System.out.println("Slot added successfully!");
	}
	
	public static void viewSlots(String gymId) {
		//get gymobject and print slots
		System.out.println("No slots to see.");
	}
	
	public static void viewMyGyms() {
		System.out.println("no gyms owned :(");
	}
	
}
