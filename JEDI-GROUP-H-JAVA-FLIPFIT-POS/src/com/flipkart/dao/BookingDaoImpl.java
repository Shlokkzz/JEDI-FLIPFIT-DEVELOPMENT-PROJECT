package com.flipkart.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.flipkart.bean.BookSlot;
import com.flipkart.bean.Slot;

public class BookingDaoImpl implements BookingDao {
	

    // Database credentials
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Flipfit";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "sai@1803";
    static SlotDaoImpl SlotDaoImplObj = new SlotDaoImpl();

    // Method to create a new database connection
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    @Override
    public Boolean deleteBooking(String bookId) {
        String deleteQuery = "DELETE FROM bookSlot WHERE bookId = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {

            // Set the value for the placeholder
            preparedStatement.setString(1, bookId);

            // Execute the update
            int rowsAffected = preparedStatement.executeUpdate();

            // Return true if at least one row is deleted
            return rowsAffected > 0;

        } catch (SQLException e) {
            // Log and handle the exception appropriately
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void createBooking(String bookingId, String userId, String slotId, String paymentId, String status) {
        String insertQuery = "INSERT INTO bookSlot (bookingId, userId, slotId, paymentId, status) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            // Set values for the placeholders
            preparedStatement.setString(1, bookingId);
            preparedStatement.setString(2, userId);
            preparedStatement.setString(3, slotId);
            preparedStatement.setString(4, paymentId);
            preparedStatement.setString(5, status);

            // Execute the update
            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Booking created successfully.");
            } else {
                System.out.println("Failed to create booking.");
            }

        } catch (SQLException e) {
            // Log and handle the exception appropriately
            e.printStackTrace();
        }
    }

    @Override
    public BookSlot getBookingById(String bookingId) {
        String selectQuery = "SELECT bookingId, userId, slotId, paymentId, status FROM bookSlot WHERE bookingId = ?";
        
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {

            // Set the value for the placeholder
            preparedStatement.setString(1, bookingId);

            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();

            // If a record is found, create and return a BookSlot object
            if (resultSet.next()) {
                BookSlot bookSlot = new BookSlot();
                bookSlot.setBookingId(resultSet.getString("bookingId"));
                bookSlot.setUserId(resultSet.getString("userId"));
                
                //get slot from SlotDaoImpl 
                Slot slot = SlotDaoImplObj.getSlotById(resultSet.getString("slotId"));
                bookSlot.setSlot(slot);
                bookSlot.setPaymentId(resultSet.getString("paymentId"));
                bookSlot.setStatus(resultSet.getString("status"));

                return bookSlot;
            }

        } catch (SQLException e) {
            // Log and handle the exception appropriately
            e.printStackTrace();
        }

        // Return null if no booking is found
        return null;
    }


    @Override
    public List<BookSlot> getBookingsforCustomer(String customerId) {
        String selectQuery = "SELECT bookingId, userId, slotId, paymentId, status FROM bookSlot WHERE userId = ?";
        List<BookSlot> bookings = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {

            // Set the value for the placeholder
            preparedStatement.setString(1, customerId);

            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Iterate through the result set and populate the list
            while (resultSet.next()) {
                BookSlot bookSlot = new BookSlot();
                bookSlot.setBookingId(resultSet.getString("bookingId"));
                bookSlot.setUserId(resultSet.getString("userId"));
                bookSlot.setSlotId(resultSet.getString("slotId"));
                bookSlot.setPaymentId(resultSet.getString("paymentId"));
                bookSlot.setStatus(resultSet.getString("status"));

                bookings.add(bookSlot);
            }

        } catch (SQLException e) {
            // Log and handle the exception appropriately
            e.printStackTrace();
        }

        // Return the list of bookings
        return bookings;
    }


    @Override
    public String doesUserHavePrevBooking(Slot slot, String userId) {
        // TODO: Implement this method
    	
    	//getting slot 
    	SlotDaoImplObj.getSlotById(slot)
        return null;
    }
}
