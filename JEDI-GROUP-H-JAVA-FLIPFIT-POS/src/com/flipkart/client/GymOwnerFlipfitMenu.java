package com.flipkart.client;

import com.flipkart.bean.GymOwner;
import com.flipkart.business.GymOwnerService;
import com.flipkart.business.UserService;
import com.flipkart.bean.User;

import java.util.Scanner;

/**
 * This class manages the menu and actions available to a Gym Owner.
 * It provides functionality for registering a gym owner, viewing and adding gym centers,
 * editing slots, and changing passwords.
 */
public class GymOwnerFlipfitMenu {
    private Scanner scanner; // Scanner instance for user input
    private GymOwnerService gymOwnerServiceInterface; // Service for managing gym owner operations
    private UserService userServiceInterface; // Service for managing user operations

    /**
     * Constructor to initialize the GymOwnerFlipfitMenu with Scanner, GymOwnerService, and UserService.
     *
     * @param scanner Scanner instance for user input
     */
    public GymOwnerFlipfitMenu(Scanner scanner) {
        this.scanner = scanner;
        this.gymOwnerServiceInterface = new GymOwnerService();
        this.userServiceInterface = new UserService();
    }

    /**
     * Registers a new gym owner by taking input from the user and calling the service to create a gym owner.
     *
     * @param scanner Scanner instance for user input
     */
    public void registerGymOwner(Scanner scanner) {
        System.out.println("Enter your Username");
        String username = scanner.nextLine();
        System.out.println("Enter your Password");
        String password = scanner.nextLine();
        System.out.println("Enter your Name");
        String name = scanner.nextLine();
        System.out.println("Enter your Phone");
        String phone = scanner.nextLine();
        System.out.println("Enter your Email");
        String mail = scanner.nextLine();
        System.out.println("Enter your Age");
        int age = Integer.parseInt(scanner.nextLine());

        gymOwnerServiceInterface.createGymOwner(username, name, mail, phone, age, password);
    }

    // This method is commented out but can be used to show the list of gym owners.
    // public void showGymOwnerList() {
    //     gymOwnerServiceInterface.listGymOwners();
    // }

    /**
     * Displays the Gym Owner menu and handles user choices.
     * Provides options to view all gym centers, add a new gym center, edit gym slots, and logout.
     *
     * @param gymOwner The currently logged-in gym owner user
     */
    public void showMenu(GymOwner gymOwner) {
        int gymOwnerChoice = -1;

        while (gymOwnerChoice != 5) { // Updated to 4 for logout
            System.out.println("Gym Owner Menu:");
            System.out.println("1. View all Gym Centers");
            System.out.println("2. Add New Gym Center");
            System.out.println("3. Edit Gym Slots");
            System.out.println("4. Change Password");
            System.out.println("5. Logout");
            System.out.print("Enter your choice: ");
            gymOwnerChoice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (gymOwnerChoice) {
                case 1:
                    gymOwnerServiceInterface.showGymCenters(gymOwner);
                    break;
                case 2:
                    addGym(gymOwner);
                    break;
                case 3:
                    gymOwnerServiceInterface.editSlots(gymOwner);
                    break;
                case 4:
                    changePassword(gymOwner);
                    break;
                case 5:
                    System.out.println("Logging out.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Changes the password for the currently logged-in user.
     * Prompts for old password, new password, and confirmation, then updates if valid.
     *
     * @param user The currently logged-in user
     */
    public void changePassword(User user) {
        System.out.println("Enter your Old Password");
        String password = scanner.nextLine();
        boolean flag = userServiceInterface.validatePassword(user, password);
        if (flag) {
            System.out.println("Enter your New Password");
            String newPassword = scanner.nextLine();
            System.out.println("Confirm your Password");
            String confirmPassword = scanner.nextLine();
            userServiceInterface.confirmPassword(user, newPassword, confirmPassword);
        } else {
            System.out.println("Wrong Old Password.");
        }
    }

    public void addGym(GymOwner gymOwner) {
        System.out.println("Registering Gym Center");
        System.out.println("Enter Gym Centre Name: ");
        String gymName = scanner.nextLine();
        System.out.println("Enter Gym Centre Address: ");
        String address = scanner.nextLine();
        System.out.println("Enter Gym Centre City: ");
        String city = scanner.nextLine();
        if(gymOwnerServiceInterface.addGymCenter(gymOwner, gymName, address, city)){
//            System.out.println("Gym Center registered successfully.");
        }
        else {
            System.out.println("Error registering Gym Center.");
        }

    }
}
