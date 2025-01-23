package com.flipkart.client;

import java.util.*;
import com.flipkart.business.*;

public class MainMenu {
	public static void main(String[] args) {
        
		Scanner scanner = new Scanner(System.in);
        mainMenu(scanner);
        scanner.close();
        
    }
	
	private static void mainMenu(Scanner scanner) {
        System.out.println("Welcome to Flipfit Application: ");
        System.out.println("1. Login");
        System.out.println("2. Registration of Gym Customer");
        System.out.println("3. Registration of Gym Owner");
        System.out.println("4. Change Password");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        String bekaar = scanner.nextLine();
            
        switch (choice) {
        	case 1:  
        		login(scanner);
                break;
        	case 2:  
        		System.out.println("Registering Gym Customer...");
        		register(scanner, "customer");
        		break;
        	case 3:  
        		System.out.println("Registering Gym Owner...");
        		register(scanner, "gymowner");
        		break;
        	case 4: 
        		System.out.println("Changing Password...");
        		changePassword(scanner);
        		break;
            case 5:  // Exit
                System.out.println("Exiting the application...");
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
                mainMenu(scanner);
        }    
        
	}

    private static void login(Scanner scanner) {
        System.out.print("Enter the Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter the Password: ");
        String password = scanner.nextLine();
        System.out.print("Enter the Role (GymAdmin/GymOwner/Customer): ");
        String role = scanner.nextLine().toLowerCase();

        // Validate the role
        
        if (role.equals("customer")) {
            displayCustomerMenu(scanner);
        } 
        else if (role.equals("gymowner")) {
            displayGymOwnerMenu(scanner);
        } 
        else if (role.equals("gymadmin")) {
            displayGymAdminMenu(scanner);
        } 
        else {
            System.out.println("Invalid role. Please try again.");
        }
    }
    
    private static void register(Scanner scanner, String role) {
        System.out.print("Enter the Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter the Password: ");
        String password = scanner.nextLine();
        
        if(role.equals("customer")) {
        	CustomerOperations.createCustomer();//modify signature to accept username pwd
        }
        else {
        	
        }
        //create user
        System.out.println("Registration successful");
        mainMenu(scanner);
    }
    
    private static void changePassword(Scanner scanner) {
    	System.out.print("Enter the Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter new Password: ");
        String password = scanner.nextLine();
        

        //create user
        System.out.println("Password changed successfully");
        mainMenu(scanner);
    }
    
    private static void displayCustomerMenu(Scanner scanner) {
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
        		mainMenu(scanner);
        		return;
        	default:
        		System.out.println("Invalid choice!");
        }
        displayCustomerMenu(scanner);
    }

    private static void displayGymOwnerMenu(Scanner scanner) {
        System.out.println("Welcome Gym Owner! Here are your options:");
        System.out.println("1. Add New Gym");
        System.out.println("2. Manage Gym Slots");
        System.out.println("3. Logout");
        int choice = scanner.nextInt();
        switch(choice) {
        	case 1:
        		System.out.println("Your gym DNE");
        		//or show if exists
        		break;
        	case 2:
        		System.out.println("Your schedule DNE");
        		//idk how to book slot
        		break;
        	case 3:
        		System.out.println("Logged Out.");
        		mainMenu(scanner);
        		break;
        	default:
        		System.out.println("Invalid choice!");
        }
        displayGymOwnerMenu(scanner);
    }

    private static void displayGymAdminMenu(Scanner scanner) {
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
	        				System.out.println("Enter userId to approve:");
	        				String userId = scanner.next();
	        				GymAdminOperations.approveGymOwner(userId);
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
        		mainMenu(scanner);
        		return;
        	default:
        		System.out.println("Invalid choice!");
        }
        displayGymAdminMenu(scanner);
    }
}
