// Package declaration
package com.flipkart.client;

// Import necessary classes
import com.flipkart.business.AdminService;
import java.util.Scanner;
import com.flipkart.bean.GymCenter;
import com.flipkart.bean.GymOwner;
import com.flipkart.bean.Admin;
import com.flipkart.bean.User;
import com.flipkart.business.AdminServiceInterface;
import com.flipkart.business.UserService;

/**
 * This class represents the menu interface for the admin to manage various
 * operations related to gym centers, gym owners, and users in the Flipfit system.
 */
public class AdminFlipfitMenu {

    private Scanner scanner;

    // AdminServiceInterface instance to handle admin-related operations
    private final AdminServiceInterface adminServiceInterface;

    // UserService instance to handle user-related operations
    private UserService userServiceInterface;

    /**
     * Constructor initializes the scanner and admin service interface.
     */
    public AdminFlipfitMenu() {
        this.scanner = new Scanner(System.in);
        this.adminServiceInterface = new AdminService();
    }

    /**
     * Displays the admin menu and handles the admin's choices.
     *
     * @param user the admin user
     */
    public void showMenu(User user) {
        int adminChoice = -1;

        // Loop until the admin chooses to log out
        while (adminChoice != 9) {
            // Display admin menu options
            System.out.println("Admin Menu:");
            System.out.println("1. List pending Gym Owners");
            System.out.println("2. List pending Gym Center");
            System.out.println("3. Approve Gym Center");
            System.out.println("4. Approve Gym Owner");
            System.out.println("5. List Gym Owners");
            System.out.println("6. List Gym Centers");
            System.out.println("7. List Users");
            System.out.println("8. Change Password");
            System.out.println("9. Logout");
            System.out.print("Enter your choice: ");
            adminChoice = scanner.nextInt();
            scanner.nextLine(); // consume the newline

            // Handle admin's choice
            switch (adminChoice) {
                case 1:
                    adminServiceInterface.listPendingGymOwners();
                    break;
                case 2:
                    adminServiceInterface.listPendingGymCenters();
                    break;
                case 3:
                    showApproveGymCenterMenu();
                    break;
                case 4:
                    showApproveGymOwnerMenu();
                    break;
                case 5:
                    adminServiceInterface.listGymOwners();
                    break;
                case 6:
                    adminServiceInterface.listGymCenters();
                    break;
                case 7:
                    adminServiceInterface.listUsers();
                    break;
                case 8:
                    changePassword(user);
                    break;
                case 9:
                    System.out.println("Logging out.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Displays the menu for approving gym centers and handles the choices.
     */
    private void showApproveGymCenterMenu() {
        int approveChoice = -1;

        // Loop until the admin chooses to go back to the main admin menu
        while (approveChoice != 3) {
            // Display options for approving gym centers
            System.out.println("Approve Gym Center:");
            System.out.println("1. Approve All Gym Centers");
            System.out.println("2. Approve Gym Center by ID");
            System.out.println("3. Back to Admin Menu");
            System.out.print("Enter your choice: ");
            approveChoice = scanner.nextInt();
            scanner.nextLine();

            // Handle admin's choice
            switch (approveChoice) {
                case 1:
                    adminServiceInterface.approveAllGymCenters();
                    break;
                case 2:
                    System.out.print("Enter Gym Center ID: ");
                    String centerId = scanner.nextLine();
                    adminServiceInterface.approveGymCenterById(centerId);
                    break;
                case 3:
                    System.out.println("Returning to Admin Menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Displays the menu for approving gym owners and handles the choices.
     */
    private void showApproveGymOwnerMenu() {
        int approveChoice = -1;

        // Loop until the admin chooses to go back to the main admin menu
        while (approveChoice != 3) {
            // Display options for approving gym owners
            System.out.println("Approve Gym Owner:");
            System.out.println("1. Approve All Gym Owners");
            System.out.println("2. Approve Gym Owner by ID");
            System.out.println("3. Back to Admin Menu");
            System.out.print("Enter your choice: ");
            approveChoice = scanner.nextInt();
            scanner.nextLine(); 

            // Handle admin's choice
            switch (approveChoice) {
                case 1:
                    adminServiceInterface.approveAllGymOwners();
                    break;
                case 2:

                    System.out.print("Enter Gym Owner ID: ");
                    String Id = scanner.nextLine();
                    adminServiceInterface.approveGymOwnerById(Id);
                    break;
                case 3:
                    System.out.println("Returning to Admin Menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Allows the admin to change their password.
     *
     * @param user the admin user
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
}
