package com.flipkart.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UserDaoImpl implements UserDao {

    // Database credentials
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Flipfit";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "sai@1803";

    // Method to create a new database connection
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    @Override
    public String verifyUser(String userName, String password, String role) {
        String sql = "SELECT userId FROM User WHERE name = ? AND password = ? AND role = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, role);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("userId");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception
        }
        return "Invalid";
    }

    @Override
    public Boolean updateUser(String userId, int choice, String updateValue) {
        String sql = null;
        switch (choice) {
            case 1: // Update username
                sql = "UPDATE User SET name = ? WHERE userId = ?";
                break;
            case 2: // Update email
                sql = "UPDATE User SET email = ? WHERE userId = ?";
                break;
            case 3: // Update password
                sql = "UPDATE User SET password = ? WHERE userId = ?";
                break;
            default:
                return false; // Invalid choice
        }

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, updateValue);
            preparedStatement.setString(2, userId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception
        }
        return false;
    }

    @Override
    public Boolean deleteUser(String userId) {
        String sql = "DELETE FROM User WHERE userId = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, userId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception
        }
        return false;
    }

    @Override
    public Boolean createUser(String username, String email, String password, String role) {
        String sql = "INSERT INTO User (userId, name, email, password, role) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            String userId = UUID.randomUUID().toString(); // Generate a unique userId

            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, password);
            preparedStatement.setString(5, role);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception
        }
        return false;
    }
}
