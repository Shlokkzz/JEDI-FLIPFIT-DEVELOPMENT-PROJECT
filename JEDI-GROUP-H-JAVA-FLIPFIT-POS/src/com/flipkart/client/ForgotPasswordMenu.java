package com.flipkart.client;

import com.flipkart.business.ForgotPasswordService;

import java.util.Scanner;

/**
 * This class handles the forgot password functionality for users.
 * It provides the interface for users to reset their password if they forget it.
 */
public class ForgotPasswordMenu {
    private Scanner scanner;
    private ForgotPasswordService forgotPasswordService; // Service to handle forgot password logic

    /**
     * Constructor to initialize the ForgotPasswordMenu with a Scanner and ForgotPasswordService.
     *
     * @param scanner Scanner instance for user input
     */
    public ForgotPasswordMenu(Scanner scanner){
        this.scanner = scanner;
        this.forgotPasswordService = new ForgotPasswordService();
    }

    /**
     * Handles the forgot password process.
     * Prompts the user to enter their username and new password,
     * then updates the password if the username is valid.
     *
     * @param scanner Scanner instance for user input
     */
    public void forgotpassword(Scanner scanner) {
        System.out.println("Enter username: ");
        String username = scanner.nextLine();

        // Check if the user exists
        if (forgotPasswordService.isUser(username)) {
            System.out.println("Enter new password: ");
            String newPass = scanner.nextLine();

            // Reset the user's password
            forgotPasswordService.resetPass(username, newPass);
            System.out.println("Password changed successfully.");
        } else {
            System.out.println("Username is not found.");
        }
    }
}
