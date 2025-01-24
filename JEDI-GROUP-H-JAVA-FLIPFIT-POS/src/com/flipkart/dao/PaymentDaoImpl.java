package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentDao {

    // Method to make a payment from sender to receiver and return the payment ID
    public int makePayment(int receiverId, int senderId, int amount) {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            // Establish the database connection
            con = DemoJdbc.connect();

            // 1. Get the current max payment_id
            String maxPaymentIdQuery = "SELECT MAX(payment_id) AS max_id FROM Payments";
            statement = con.prepareStatement(maxPaymentIdQuery);
            rs = statement.executeQuery();

            int newPaymentId = 1;  // Default to 1 if no payments exist yet

            // If there are records, we can increment the max payment_id
            if (rs.next()) {
                newPaymentId = rs.getInt("max_id") + 1;
            }

            // 2. Insert the new payment record with the generated payment_id
            String insertPaymentQuery = "INSERT INTO Payments (payment_id, sender_id, receiver_id, amount, status) VALUES (?, ?, ?, ?, 'pending')";
            statement = con.prepareStatement(insertPaymentQuery);
            statement.setInt(1, newPaymentId); // Set the generated payment_id
            statement.setInt(2, senderId);
            statement.setInt(3, receiverId);
            statement.setInt(4, amount);

            // Execute the insert statement
            int rowsAffected = statement.executeUpdate();

            // 3. Return the generated payment_id if insertion was successful
            if (rowsAffected > 0) {
                return newPaymentId;
            }

            return -1; // In case of failure

        } catch (SQLException se) {
            se.printStackTrace();
            return -1; // Return -1 if there's an error
        } catch (Exception e) {
            e.printStackTrace();
            return -1; // Return -1 if there's an error
        } finally {
            try {
                if (rs != null) rs.close();
                if (statement != null) statement.close();
                if (con != null) con.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
