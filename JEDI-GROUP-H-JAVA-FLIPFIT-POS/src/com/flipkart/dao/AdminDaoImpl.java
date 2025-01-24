package com.flipkart.dao;
import com.flipkart.bean.*;
import com.flipkart.exception.DAOException;
import com.flipkart.utils.dbutils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDAOImpl {
    Connection connection;
//    public List<GymOwner> getAllGymOwners() {
//        List<GymOwner> gymOwners = new ArrayList<GymOwner>();
//        String query = "select name, email, contactNo, age from gymOwner";
//        try {connection = dbutils.getConnection();
//            // Step 2:Create a statement using connection object
//            PreparedStatement = connection.prepareStatement(query);
//            System.out.println();
//            // Step 3: Execute the query or update query
//            ResultSet rs = preparedStatement.executeQuery();
//
//            // Step 4: Process the ResultSet object.
//            while (rs.next()) {
//                GymOwner gymOwner = new GymOwner();
//                gymOwner.setEmail(rs.getString("email"));
//                gymOwner.setName(rs.getString("name"));
//                gymOwner.setPhoneNumber(rs.getString("phoneNum"));
//                gymOwner.setAadharNumber(rs.getString("aadharNum"));
//                gymOwner.setPanNumber(rs.getString("panNum"));
//                gymOwner.setVerified(rs.getBoolean("isVerified"));
//                gymOwners.add(gymOwner);
////	                System.out.println(id + "," + name + "," + email + "," + country + "," + password);
//            }
//        } catch (SQLException e) {
//            printSQLException(e);
//        }
//        // Step 4: try-with-resource statement will auto close the connection.
//        return gymOwners;
//    }

    public List<Customer> getAllCustomers() throws DAOException {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM customer";

        try{
            connection = dbutils.getConnection();
            PreparedStatement prep = connection.prepareStatement(query);
        } catch (SQLException e) {
            System.err.println("Error fetching gym owners: " + e.getMessage());
            throw new DAOException("Error fetching gym owners", e);
        } finally {
        }

        return customers;
    }
    public List<GymOwner> getPendingGymOwners() {
        List<GymOwner> pendingGymOwners = new ArrayList<>();
        String sql = "SELECT * FROM gym_owner WHERE approval = ?";

        try (Connection connection = dbutils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, 0);
             ResultSet resultSet = statement.executeQuery();
                while(resultSet.next()) {
                    String username = resultSet.getString("username");
                    String userid = resultSet.getString("userid");
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");
                    String contactNo = resultSet.getString("contactNo");
                    int age = resultSet.getInt("age");

                    GymOwner gymO = new GymOwner(username, name, email, contactNo, age, "password", userid, "B");
                    pendingGymOwners.add(gymO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            dbutils.closeConnection();
        }
        return pendingGymOwners;
    }

    public List<GymOwner> getAllGymOwners() {
        List<GymOwner> gymOwners = new ArrayList<>();
        String sql = "SELECT * FROM gym_owner";

        try (Connection connection = dbutils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

             ResultSet resultSet = statement.executeQuery();

                while(resultSet.next()) {
                    String username = resultSet.getString("username");
                    String userid = resultSet.getString("userid");
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");
                    String contactNo = resultSet.getString("contactNo");
                    int age = resultSet.getInt("age");

                    GymOwner gymO = new GymOwner(username, name, email, contactNo, age, "password", userid, "B");
                    gymOwners.add(gymO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            dbutils.closeConnection();
        }
        return gymOwners;
    }


    public boolean approveGymOwner(String userID){
        String sql = "UPDATE gym_owner SET approval = ? WHERE userid = ?";
        try(Connection conn = dbutils.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setInt(1,1);
            ps.setString(2,userID);
            int rowsAffected = ps.executeUpdate();
            if(rowsAffected>0){
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dbutils.closeConnection();
        }
        return false;
    }

    public boolean approveAllGymOwner(){
        String sql = "UPDATE gym_owner SET approval = ?";
        try(Connection conn = dbutils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setInt(1,1);
            int rowsAffected = ps.executeUpdate();
            if(rowsAffected>0){
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dbutils.closeConnection();
        }
        return false;
    }

    public boolean approveAllGymCenter() {
        String sql = "UPDATE gym_center SET approval = ?";
        try(Connection conn = dbutils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setInt(1,1);
            int rowsAffected = ps.executeUpdate();
            if(rowsAffected>0){
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dbutils.closeConnection();
        }
        return false;
    }

    public boolean approveGymCenter(String gymID){
        String sql = "UPDATE gym_center SET approval = ? WHERE gymID = ?";
        try(Connection conn = dbutils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setInt(1,1);
            ps.setString(2,gymID);
            int rowsAffected = ps.executeUpdate();
            if(rowsAffected>0){
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dbutils.closeConnection();
        }
        return false;
    }

    public List<GymCenter> getPendingGymCenters() {
        List<GymCenter> pendingGymCenters = new ArrayList<>();
        String sql = "SELECT * FROM gym_center WHERE approval = ?";

        try (Connection connection = dbutils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, 0); // Assuming user.getId() gives the gymOwnerID

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String gymID = resultSet.getString("gymID");
                String gymName = resultSet.getString("gymName");
                String address = resultSet.getString("address");
                String city = resultSet.getString("city");
                String gymOwnerID = resultSet.getString("gymOwnerID");
                int approval = resultSet.getInt("approval");
                List<Slot> slots = new ArrayList<>();
                GymCenter gymCenter = new GymCenter(gymID, gymName, address, city, slots, gymOwnerID);
                pendingGymCenters.add(gymCenter);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbutils.closeConnection();
        }
        return pendingGymCenters;
    }

    public List<GymCenter> getAllGymCenters() {
        List<GymCenter> gymCenters = new ArrayList<>();
        String sql = "SELECT * FROM gym_center";

        try (Connection connection = dbutils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String gymID = resultSet.getString("gymID");
                String gymName = resultSet.getString("gymName");
                String address = resultSet.getString("address");
                String city = resultSet.getString("city");
                String gymOwnerID = resultSet.getString("gymOwnerID");
                int approval = resultSet.getInt("approval");
                List<Slot> slots = new ArrayList<>();
                GymCenter gymCenter = new GymCenter(gymID, gymName, address, city, slots, gymOwnerID);
                gymCenters.add(gymCenter);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbutils.closeConnection();
        }
        return gymCenters;
    }

    public List<User> getAllUser()
    {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM user";

        try (Connection connection = dbutils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
             ResultSet resultSet = statement.executeQuery();

                while(resultSet.next()) {
                    String username = resultSet.getString("username");
                    String userid = resultSet.getString("userid");
                    String roleID = resultSet.getString("roleID");
                    String password = resultSet.getString("password");
                    User user = new User(username, password, userid, roleID);
                    users.add(user);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            dbutils.closeConnection();
        }
        return users;
    }

}
