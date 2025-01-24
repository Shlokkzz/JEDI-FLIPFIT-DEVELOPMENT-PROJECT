package com.flipkart.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.flipkart.bean.GymCentre;

public class GymDaoImpl {

    // Database credentials
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Flipfit";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "sai@1803";

    // Method to create a new database connection
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    // Method to retrieve all gyms
    public List<GymCentre> getAllGyms() {
        String selectQuery = "SELECT gymId, name, location, capacity, isOpen FROM gym";
        List<GymCentre> gyms = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            // Iterate through the result set and populate the list
            while (resultSet.next()) {
                GymCentre gymCentre = new GymCentre();
                gymCentre.setGymId(resultSet.getString("gymId"));
                gymCentre.setName(resultSet.getString("name"));
                gymCentre.setLocation(resultSet.getString("location"));
                
                //get all slots by a gym 
                

                gyms.add(gymCentre);
            }

        } catch (SQLException e) {
            // Log and handle the exception appropriately
            e.printStackTrace();
        }

        // Return the list of gyms
        return gyms;
    }
}
