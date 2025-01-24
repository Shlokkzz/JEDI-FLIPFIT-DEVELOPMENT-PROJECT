package com.flipkart.dao;

import com.flipkart.bean.*;
import com.flipkart.exception.DAOException;
import com.flipkart.utils.dbutils;

//import java.awt.print.Book;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerDAOImpl implements CustomerDAO {

//    public void cancelBooking(String GymId ,String slotId, String userId, String date){
//        Booking bookingToCancel = null;
//        LocalDate cancellationDate = LocalDate.parse(date);
//
//        for (Booking booking : bookings) {
//            if (booking.getGymID().equals(GymId) && booking.getSlotID().equals(slotId) && booking.getUserID().equals(userId) && booking.getDate().toLocalDate().equals(cancellationDate)) {
//                bookingToCancel = booking;
//                break;
//            }
//        }
//
//        if (bookingToCancel == null) {
//            System.out.println("Booking not found!");
//            return;
//        }
//
//        List<Slot> slots = gymSlots.get(bookingToCancel.getGymID());
//        if (slots != null) {
//            for (Slot slot : slots) {
//                if (slot.getSlotID().equals(bookingToCancel.getSlotID())) {
//                    slot.increaseCapacity();
//                    break;
//                }
//            }
//        }
//
//        bookings.remove(bookingToCancel);
//        System.out.println("Booking canceled successfully!");
//    }
//



    public Customer getCustomer(User user) {
        Customer customer = null;
        String sql = "SELECT * FROM customer WHERE username = ?";
        List<Booking> bookings = null;
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

                    customer = new Customer(username, name, email, contactNo, age, user.getPassword(), userid, user.getRoleId(), bookings);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    public boolean updateProfile(Customer customer){
        String sql = "UPDATE customer SET username = ?, name = ?, email = ?, contactNo = ?, age = ? WHERE userid = ?";

        try (Connection connection = dbutils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, customer.getUsername());
            statement.setString(2, customer.getName());
            statement.setString(3, customer.getEmail());
            statement.setString(4, customer.getPhone());
            statement.setInt(5, customer.getAge());
            statement.setString(6, customer.getUserid());

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addBooking(Booking booking) {
        String sql = "INSERT INTO booking (userID, bookingID, gymID, slotID, date) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = dbutils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // Set the parameters
            ps.setString(1, booking.getUserID());
            ps.setString(2, booking.getBookingID());
            ps.setString(3, booking.getGymID());
            ps.setString(4, booking.getSlotID());

            // Set the current date and time for the booking
            ps.setTimestamp(5, java.sql.Timestamp.valueOf(LocalDateTime.now()));

            // Execute the insert operation
            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            dbutils.closeConnection();
        }
        return false;
    }

    public boolean bookingExists(Booking booking) {
        String sql = "SELECT COUNT(*) FROM booking WHERE slotID = ? AND userID = ?";

        try (Connection conn = dbutils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // Set the parameters
            ps.setString(1, booking.getSlotID());
            ps.setString(2, booking.getUserID());

            // Execute the query
            ResultSet rs = ps.executeQuery();

            // Check if a record exists
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbutils.closeConnection();
        }
        return false;
    }

    public boolean cancelBooking(String userID, String slotID) {
        String deleteBookingSql = "DELETE FROM booking WHERE userID = ? AND slotID = ?";

        try (Connection conn = dbutils.getConnection();
             PreparedStatement deleteBookingStmt = conn.prepareStatement(deleteBookingSql)) {

                // Delete the booking
                deleteBookingStmt.setString(1, userID);
                deleteBookingStmt.setString(2, slotID);
                int rowsDeleted = deleteBookingStmt.executeUpdate();

                if (rowsDeleted > 0) {
                    // Update the slot capacity
                    updateCapacity(slotID, 1);
                    return true;
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateCapacity(String slotID, int delta) {
        String sql = "UPDATE slot SET capacity = capacity + ? WHERE slotID = ?";

        try (Connection conn = dbutils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // Set the parameters
            ps.setInt(1, delta);
            ps.setString(2, slotID);

            // Execute the update
            int rowsAffected = ps.executeUpdate();

            // Return true if one or more rows were updated
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbutils.closeConnection();
        }
        return false;
    }

    public List<Booking> viewBookings(String userId) {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT b.bookingID, b.gymID, b.slotID, b.date, gc.gymName, s.startTime, s.endTime, s.capacity " +
                "FROM booking b " +
                "JOIN gym_center gc ON b.gymID = gc.gymID " +
                "JOIN slot s ON b.slotID = s.slotID " +
                "WHERE b.userID = ?";

        try (Connection conn = dbutils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String bookingId = rs.getString("bookingID");
                String gymId = rs.getString("gymID");
                String slotId = rs.getString("slotID");
                String gymName = rs.getString("gymName");

                Timestamp startTimeTs = rs.getTimestamp("startTime");
                Timestamp endTimeTs = rs.getTimestamp("endTime");
                LocalDateTime startTime = startTimeTs.toLocalDateTime();
                LocalDateTime endTime = endTimeTs.toLocalDateTime();

                int capacity = rs.getInt("capacity");
                Timestamp dateTs = rs.getTimestamp("date");
                LocalDateTime date = dateTs.toLocalDateTime();

                Slot slot = new Slot(slotId, startTime, endTime, capacity, gymId);
                Booking booking = new Booking(userId, bookingId, gymId, slotId, gymName, slot, date);
                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbutils.closeConnection();
        }

        return bookings;
    }

}
