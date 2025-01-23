package com.flipkart.client;

import java.util.Scanner;

import com.flipkart.business.CustomerOperations;

public class CustomerMenu {
	public static void displayCustomerMenu(Scanner scanner) {
        System.out.println("Welcome Customer! Here are your options:");
        System.out.println("1. View Active Gym Bookings");
        System.out.println("2. View All Gyms");
        System.out.println("3. Update Profile");
        System.out.println("4. Logout");
        
        int choice = scanner.nextInt();
        switch(choice) {
        	case 1:
        		CustomerOperations.viewBooking("");
        		System.out.println("Your bookings DNE");
        		//or show if exists
        		break;
        	case 2:
        		CustomerOperations.requestBooking(null, null, null);//db se data pull karke karenge kuch
        		System.out.println("Your booking can't be done");
        		//idk how to book slot
        		break;
        	case 3:
        		CustomerOperations.updateCustomer(0); // id
        		System.out.println("Profile can't be updated");
        		//sochte hai
        		break;
        	case 4:
        		System.out.println("Logged Out.");
        		MainMenu.displayMainMenu(scanner);
        		return;
        	default:
        		System.out.println("Invalid choice!");
        }
        displayCustomerMenu(scanner);
    }
}
