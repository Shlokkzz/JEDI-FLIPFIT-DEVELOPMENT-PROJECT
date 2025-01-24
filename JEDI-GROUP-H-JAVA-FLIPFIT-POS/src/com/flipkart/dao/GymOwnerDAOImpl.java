package com.flipkart.dao;

import com.flipkart.bean.*;

import com.flipkart.client.Formatting;
import com.flipkart.utils.dbutils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GymOwnerDAOImpl implements GymOwnerDAO {
    public boolean addGymCenter(GymCenter gymCenter) {
        String sql = "INSERT INTO gym_center (gymID,gymName,address,city,gymOwnerID,approval) VALUES (?,?,?,?,?,?)";
//        String sql = "INSERT INTO Customeromers (username, name, email, contactNo, age, password, ID) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = dbutils.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, gymCenter.getGymID());
            statement.setString(2, gymCenter.getGymName());
            statement.setString(3, gymCenter.getAddress());
            statement.setString(4, gymCenter.getCity());
            statement.setString(5, gymCenter.getGymOwnerID());
            statement.setInt(6,0);
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbutils.closeConnection();
        }
        return false;
    }
    public List<GymCenter> getGymCenters(String userid) {
        List<GymCenter> gymCenters = new ArrayList<>();
        String sql = "SELECT * FROM gym_center WHERE gymOwnerID = ?";

        try (Connection connection = dbutils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, userid); // Assuming user.getId() gives the gymOwnerID

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String gymID = resultSet.getString("gymID");
                String gymName = resultSet.getString("gymName");
                String address = resultSet.getString("address");
                String city = resultSet.getString("city");
                String gymOwnerID = resultSet.getString("gymOwnerID");
                int approval = resultSet.getInt("approval");
                if(approval==1) {
                    List<Slot> slots = new ArrayList<>();
                    GymCenter gymCenter = new GymCenter(gymID, gymName, address, city, slots, gymOwnerID);
                    gymCenters.add(gymCenter);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbutils.closeConnection();
        }

        return gymCenters;
    }

    public GymOwner getGymOwner(User user) {
        GymOwner gymOwner = null;
        String sql = "SELECT * FROM gym_owner WHERE username = ?";

        try (Connection connection = dbutils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, user.getUsername());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String username = resultSet.getString("username");
                    String userid = resultSet.getString("userid");
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");
                    String contactNo = resultSet.getString("contactNo");
                    int age = resultSet.getInt("age");

                    gymOwner = new GymOwner(username, name, email, contactNo, age, user.getPassword(), userid, user.getRoleId());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gymOwner;
    }


    public void addSlots(String gymID, Slot slot) {
        // Check if the same slot is already present in the slot table for the given gymCenter
        if (isSlotExists(gymID, slot)) {
            System.out.println("Slot already exists for the given GymCenter.");
            return; // Or throw an exception or handle the situation as per your requirement
        }

        String sql = "INSERT INTO slot (slotID, starttime, endtime, capacity, gymID) VALUES (?,?,?,?,?)";

        try (Connection connection = dbutils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, slot.getSlotID()); // Assuming slot.getSlotID() retrieves the slot ID
            statement.setTimestamp(2, java.sql.Timestamp.valueOf(slot.getStarttime())); // Assuming slot.getStarttime() returns LocalDateTime
            statement.setTimestamp(3, java.sql.Timestamp.valueOf(slot.getEndtime())); // Assuming slot.getEndTime() returns a LocalTime object
            statement.setInt(4, slot.getCapacity()); // Assuming slot.getCapacity() returns the capacity
            statement.setString(5, gymID); // Assuming gymCenter.getGymID() retrieves the gymID

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                Formatting.print("Slot added successfully.");
            } else {
                Formatting.print("Failed to add slot.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean isSlotExists(String gymID, Slot slot) {
        String sql = "SELECT COUNT(*) AS count FROM slot WHERE gymID = ? AND starttime = ? AND endtime = ?";
        try (Connection connection = dbutils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, gymID);
            statement.setTimestamp(2, java.sql.Timestamp.valueOf(slot.getStarttime())); // Assuming slot.getStarttime() returns LocalDateTime
            statement.setTimestamp(3, java.sql.Timestamp.valueOf(slot.getEndtime()));

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt("count");
                return count > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void removeSlot(String gymID, LocalDateTime starttime) {
        String sql = "DELETE FROM slot WHERE gymID = ? AND starttime = ?";

        try (Connection connection = dbutils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // Convert LocalDateTime to java.sql.Timestamp for DATETIME columns
            statement.setString(1, gymID);
            statement.setTimestamp(2, java.sql.Timestamp.valueOf(starttime));

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                Formatting.print("Slot deleted successfully.");
            } else {
                Formatting.print("Slot not found or failed to delete.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
