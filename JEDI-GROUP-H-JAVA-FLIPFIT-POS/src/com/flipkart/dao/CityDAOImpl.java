package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.flipkart.bean.City;
import com.flipkart.bean.GymCenter;
import com.flipkart.bean.Slot;
import com.flipkart.utils.dbutils;


public class CityDAOImpl implements CityDAO{
    @Override
    public boolean cityExists(String cityName) {
        String sql = "SELECT 1 FROM city WHERE cityName = ?";
        try (Connection connection = dbutils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, cityName);
            ResultSet rs = statement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<City> getAllCities() {
        List<City> cities = new ArrayList<>();
        String sql = "SELECT * FROM city";

        try (Connection conn = dbutils.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                City city = new City(rs.getString("cityID"), rs.getString("cityName"));
                cities.add(city);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbutils.closeConnection();
        }

        return cities;
    }

    // Method to add a city
    @Override
    public boolean addCity(City city) {
        String sql = "INSERT INTO city (cityID, cityName) VALUES (?, ?)";
        try (Connection connection = dbutils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            String cityID = "C" + System.currentTimeMillis(); // Generate a unique city ID
            statement.setString(1, city.getCityID());
            statement.setString(2, city.getCityName());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Slot> fetchSlotsByGymID(String gymID) {
        List<Slot> slots = new ArrayList<>();
        String sql = "SELECT * FROM slot WHERE gymID = ?";

        try (Connection connection = dbutils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, gymID);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String slotID = resultSet.getString("slotID");
                    LocalDateTime startTime = resultSet.getTimestamp("startTime").toLocalDateTime();
                    LocalDateTime endTime = resultSet.getTimestamp("endTime").toLocalDateTime();
                    int capacity = resultSet.getInt("capacity");

                    Slot slot = new Slot(slotID, startTime, endTime, capacity, gymID);
                    slots.add(slot);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return slots;
    }

    @Override
    public List<GymCenter> fetchGymCenters(String city){
        List<GymCenter> gymCenters = new ArrayList<>();
        String sql = "SELECT * FROM gym_center WHERE city = ?";

        try (Connection connection = dbutils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, city);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String gymID = resultSet.getString("gymID");
                    String gymName = resultSet.getString("gymName");
                    String address = resultSet.getString("address");
                    String cityResult = resultSet.getString("city");
                    String gymOwnerId = resultSet.getString("gymOwnerID");

                    List<Slot> slots = fetchSlotsByGymID(gymID);

                    // Assuming GymCenter has a constructor matching these parameters
                    GymCenter gymCenter = new GymCenter(gymID, gymName, address, cityResult, slots, gymOwnerId);
                    gymCenters.add(gymCenter);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return gymCenters;
    }
}
