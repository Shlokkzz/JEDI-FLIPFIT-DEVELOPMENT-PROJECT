package com.flipkart.client;

import java.util.Scanner;

import com.flipkart.business.GymOwnerOperations;

public class GymOwnerMenu {
	
	static Scanner scanner=new Scanner(System.in);
	
	public static void displayGymOwnerMenu(String userId) {
        System.out.println("Welcome Gym Owner! Here are your options:");
        System.out.println("1. View All Gym");
        System.out.println("2. Add New Gym");
        System.out.println("3. Manage Gym Slots");
        System.out.println("4. Logout");
        int choice = scanner.nextInt();
        switch(choice) {
        	case 1:
        		GymOwnerOperations.viewMyGyms();
        		break;
        	case 2:
        		GymOwnerOperations.addGym(null);
        		break;
        	case 3:
        		int newChoice = 0;
        		while (newChoice != 5) {
        			System.out.println("1. View Slots");
        			System.out.println("2. Add A Slot");
        			System.out.println("3. Modify A Slot");
        			System.out.println("4. Delete A Slot");
        			System.out.println("5. Finish");
	        		newChoice = scanner.nextInt();
	        		switch(newChoice) {
		        		case 1:
	        				GymOwnerOperations.viewSlots(null);
	        				break;
		        		case 2:
	        				GymOwnerOperations.addSlot(null, null);
	        				break;
		        		case 3:
	        				GymOwnerOperations.modifySlot(null, null);
	        				break;
		        		case 4:
	        				GymOwnerOperations.deleteSlot(null, null);
	        				break;
		        		case 5:
	        				System.out.println("Slot management exited.");
	        				break;
	        			default:
	        				System.out.println("Invalid Choice!");
	        		}
        		}
        		break;
        	case 4:
        		System.out.println("Logged Out.");
        		MainMenu.displayMainMenu(scanner);
        		break;
        	default:
        		System.out.println("Invalid choice!");
        }
        displayGymOwnerMenu(userId);
	}
	

}
