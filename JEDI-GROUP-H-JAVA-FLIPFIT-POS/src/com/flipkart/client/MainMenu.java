package com.flipkart.client;

import java.util.*;
import com.flipkart.business.*;

public class MainMenu {
	public static void main(String[] args) {
        
		Scanner scanner = new Scanner(System.in);
        displayMainMenu(scanner);
        scanner.close();
        
    }
	
	public static void displayMainMenu(Scanner scanner) {
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
                displayMainMenu(scanner);
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
            CustomerMenu.displayCustomerMenu(scanner);
        } 
        else if (role.equals("gymowner")) {
            GymOwnerMenu.displayGymOwnerMenu(scanner);
        } 
        else if (role.equals("gymadmin")) {
            GymAdminMenu.displayGymAdminMenu(scanner);
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
        displayMainMenu(scanner);
    }
    
    private static void changePassword(Scanner scanner) {
    	System.out.print("Enter the Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter new Password: ");
        String password = scanner.nextLine();
        

        //create user
        System.out.println("Password changed successfully");
        displayMainMenu(scanner);
    }
}
