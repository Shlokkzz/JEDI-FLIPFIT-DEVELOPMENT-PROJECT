package com.flipkart.client;

import com.flipkart.bean.Customer;

import com.flipkart.client.Formatting;
import com.flipkart.bean.GymOwner;
import com.flipkart.bean.Role;
import com.flipkart.bean.User;
import com.flipkart.business.UserService;
import com.flipkart.dao.CustomerDAOImpl;
import com.flipkart.dao.GymOwnerDAOImpl;
import com.flipkart.dao.UserDAOImpl;
import com.flipkart.exception.InvalidLogin;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * This class is the entry point for the Flipfit application.
 * It handles user interactions for login, registration, password changes, and manages navigation
 * to different user menus based on the user role.
 */
public class FlipfitApplication {
    // Static instance of AdminFlipfitMenu to be used throughout the application
    static AdminFlipfitMenu adminFlipfitMenu = new AdminFlipfitMenu();
    static GymOwnerDAOImpl gymOwnerDAOImpl = new GymOwnerDAOImpl();
    static CustomerDAOImpl customerDAOImpl = new CustomerDAOImpl();

    /**
     * Main method to start the Flipfit application.
     * It displays the main menu and handles user choices.
     *
     * @param args command-line arguments
     * @throws InvalidLogin if login credentials are invalid
     */
    public static void main(String[] args) throws InvalidLogin {
        Scanner scanner = new Scanner(System.in);
        FlipfitApplication app = new FlipfitApplication();
        CustomerFlipfitMenu customerFlipfitMenu = new CustomerFlipfitMenu(scanner);
        ForgotPasswordMenu forgotPasswordMenu = new ForgotPasswordMenu(scanner);
        GymOwnerFlipfitMenu gymOwnerFlipfitMenu = new GymOwnerFlipfitMenu(scanner);

       

        int choice = -1;

        // Main menu loop
        while (choice != 4) {
        	System.out.println();
        	Formatting.print("Welcome to the Flipfit Application !!! ");
            System.out.println("1. Login");
            System.out.println("2. Registration of the GYM Customer");
            System.out.println("3. Registration of the GYM Owner");
//            System.out.println("4. Change Password");
            System.out.println("4. Exit");
            System.out.println("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    // Handle user login
                    handleLogin();
                    break;
                case 2:
                    // Register a new customer
                    customerFlipfitMenu.registerCustomer(scanner);
                    Formatting.print(" Customer Registered ");
                    break;
                case 3:
                    // Register a new gym owner
                    try {
                        gymOwnerFlipfitMenu.registerGymOwner(scanner);
                        Formatting.print(" Gym Owner Registered ");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
//                case 4:
//                    // Change password
//                    forgotPasswordMenu.forgotpassword(scanner);
//                    break;
                case 4:
                    // Exit the application
                    Formatting.print("Exiting the application.");
                    break;
                default:
                    System.out.println("Error : Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Handles user login, determines user role, and navigates to the appropriate menu.
     *
     * @throws InvalidLogin if the login attempt fails
     */
    public static void handleLogin() throws InvalidLogin {
        Scanner scanner = new Scanner(System.in);

        // Prompt the user for username and password
        System.out.println("Enter your username: ");
        String username = scanner.nextLine();
        UserService userService = new UserService();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();

        // Validate user credentials and get user details
        User user = userService.login(username, password);
        if (user != null) {
            Formatting.print("User: "+ username +" logged in successfully.");
            displayCurrentDateTime();
            String id = user.getUserid();
            String roleId = user.getRoleId();
            
            // Navigate to the appropriate menu based on user role
            switch (roleId) {
                case "A":
                    Formatting.print("Welcome Admin !!!");
                    adminFlipfitMenu.showMenu(user);
                    break;
                case "B":
                    Formatting.print("Welcome GymOwner !!!");
                    GymOwner gymOwner = gymOwnerDAOImpl.getGymOwner(user);
                    GymOwnerFlipfitMenu gymOwnerFlipfitMenu = new GymOwnerFlipfitMenu(scanner);
                    gymOwnerFlipfitMenu.showMenu(gymOwner);
                    break;
                case "C":
                    Formatting.print("Welcome "+username+ " !!!");
                    Customer customer = customerDAOImpl.getCustomer(user);
                    CustomerFlipfitMenu customerFlipfitMenu = new CustomerFlipfitMenu(scanner);
                    customerFlipfitMenu.showMenu(customer);
                    break;
                default:
                    System.out.println("Error : Invalid role. Please try again.");
            }
        } else {
            System.out.println("Invalid username or password.");
        }
    }
    public static void displayCurrentDateTime() {
    	LocalDateTime currentDateTime = LocalDateTime.now();  

        // Format the date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
        String formattedDateTime = currentDateTime.format(formatter);  

        // Display the current date and time
        Formatting.print("Current Date and Time: " + formattedDateTime);
		
		
	}
    
   
}
