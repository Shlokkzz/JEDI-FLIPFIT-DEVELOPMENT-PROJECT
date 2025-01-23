package com.flipkart.client;

import java.util.Scanner;

import com.flipkart.business.GymAdminOperations;

public class GymAdminMenu {
	
	static Scanner scanner=new Scanner(System.in);
	
	public static void displayGymAdminMenu(String userId) {
        System.out.println("Welcome Gym Admin! Here are your options:");
        System.out.println("1. View And Approve Gym Owners");
        System.out.println("2. View And Approve New Gyms");
        System.out.println("3. View All Gyms");
        System.out.println("4. Logout");
        
        int choice = scanner.nextInt();
        int newChoice;
        switch(choice) {
        	case 1:
        		GymAdminOperations.viewPendingGymOwners();
        		newChoice = 0;
        		while(newChoice != 2) {
	        		System.out.println("1. Approve a gym owner");
	        		System.out.println("2. Finish");
	        		newChoice = scanner.nextInt();
	        		switch(newChoice) {
	        			case 1:
	        				System.out.println("Enter gym Owner to approve:");
	        				String gymOwnerId = scanner.next();
	        				GymAdminOperations.approveGymOwner(gymOwnerId);
	        				break;
	        			case 2:
	        				System.out.println("Approval round finished.");
	        				break;
	        			default:
	        				System.out.println("Invalid choice!");
	        		}
        		}
        		break;
        	case 2:
        		GymAdminOperations.viewPendingGyms();
        		newChoice = 0;
        		while(newChoice != 2) {
	        		System.out.println("1. Approve a gym");
	        		System.out.println("2. Finish");
	        		newChoice = scanner.nextInt();
	        		switch(newChoice) {
	        			case 1:
	        				System.out.println("Enter gymId to approve:");
	        				String gymId = scanner.next();
	        				GymAdminOperations.approveGym(gymId);
	        				break;
	        			case 2:
	        				System.out.println("Approval round finished.");
	        				break;
	        			default:
	        				System.out.println("Invalid choice!");
	        		}
        		}
        		break;
        	case 3:
        		GymAdminOperations.viewGyms();
        		break;
        	case 4:
        		System.out.println("Logged Out.");
        		MainMenu.displayMainMenu(scanner);
        		return;
        	default:
        		System.out.println("Invalid choice!");
        }
        displayGymAdminMenu(userId);
    }
}
