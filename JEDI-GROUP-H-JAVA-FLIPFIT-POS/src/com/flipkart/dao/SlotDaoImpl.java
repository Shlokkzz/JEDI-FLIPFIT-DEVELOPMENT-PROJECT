package com.flipkart.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.flipkart.bean.Slot;

public class SlotDaoImpl implements SlotDao {

    // Database credentials
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Flipfit";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "sai@1803";

    // Method to create a new database connection
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    // Method to retrieve all slots where gymId matches the passed gymId
    public List<Slot> getAllSlotsByGym(String gymId) {
        String selectQuery = "SELECT slotId, gymId, startTime, endTime, isAvailable, seats FROM Slot WHERE gymId = ?";
        List<Slot> slots = new ArrayList<>();

        // DateTimeFormatter to parse the time strings
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {

            // Set the provided gymId to the query
            preparedStatement.setString(1, gymId);

            // Execute the query and get the result set
            ResultSet resultSet = preparedStatement.executeQuery();

            // Iterate through the result set and populate the list
            while (resultSet.next()) {
                Slot slot = new Slot();
                slot.setSlotId(resultSet.getString("slotId"));
                slot.setGymId(resultSet.getString("gymId"));

                // Parse the startTime and endTime strings to LocalDateTime
                String startTimeStr = resultSet.getString("startTime");
                if (startTimeStr != null) {
                    LocalDateTime startTime = LocalDateTime.parse(startTimeStr, formatter);
                    slot.setStartTime(startTime);
                }

                

                // Fetch and set the seats value
                int seats = resultSet.getInt("seats");
                slot.setSeats(seats);  // Call setter for seats

               

                slots.add(slot);
            }

        } catch (SQLException e) {
            // Log and handle the exception appropriately
            e.printStackTrace();
        }

        // Return the list of slots matching the gymId
        return slots;
    }
    
    
    public Slot getSlotByID(String slotId) {
        String selectQuery = "SELECT slotId, gymId, startTime, endTime, isAvailable, seats FROM Slot WHERE slotId = ?";
        Slot slot = null;

        // DateTimeFormatter to parse the time strings
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {

            // Set the provided slotId to the query
            preparedStatement.setString(1, slotId);

            // Execute the query and get the result set
            ResultSet resultSet = preparedStatement.executeQuery();

            // If a matching slot is found, map it to a Slot object
            if (resultSet.next()) {
                slot = new Slot();
                slot.setSlotId(resultSet.getString("slotId"));
                slot.setGymId(resultSet.getString("gymId"));

                // Parse the startTime and endTime strings to LocalDateTime
                String startTimeStr = resultSet.getString("startTime");
                if (startTimeStr != null) {
                    LocalDateTime startTime = LocalDateTime.parse(startTimeStr, formatter);
                    slot.setStartTime(startTime);
                }

               

                // Fetch and set the seats value
                int seats = resultSet.getInt("seats");
                slot.setSeats(seats);  // Call setter for seats

               
            }

        } catch (SQLException e) {
            // Log and handle the exception appropriately
            e.printStackTrace();
        }

        // Return the slot object (or null if not found)
        return slot;
    }
    
    public Slot cr
    
    



}
