package com.flipkart.bean;

import java.time.LocalDateTime;
import java.util.List;

// Represents a time slot at a gym center.
public class Slot {
    private LocalDateTime starttime;
    private LocalDateTime endtime;
    private int capacity;
    private String slotID;
    private List<Booking> bookings;
    private List<Booking> waitings;

    public String getGymID() {
        return gymID;
    }

    public void setGymID(String gymID) {
        this.gymID = gymID;
    }

    private String gymID;

    /* Constructor to initialize a Slot object.
     Parameters:
     slotID: Unique ID of the slot.
     starttime: Start time of the slot.
     endtime: End time of the slot.
     capacity: Capacity of the slot.
     bookings: List of bookings already made for this slot.
     waitings: List of bookings on waiting list for this slot.
     */
    public Slot(String slotID, LocalDateTime starttime, LocalDateTime endtime, int capacity, String gymID) {
        this.slotID = slotID;
        this.starttime = starttime;
        this.endtime = endtime;
        this.capacity = capacity;
        this.gymID = gymID;
//        this.bookings = bookings != null ? bookings : new ArrayList<>();   // Initialize bookings list if null.
//        this.waitings = waitings != null ? waitings : new ArrayList<>();   // Initialize waitings list if null.
    }

//    public Slot(String slotID, LocalDateTime startTime, LocalDateTime endTime, int capacity) {
//        this.slotID = slotID;
//        this.starttime = starttime;
//        this.endtime = endtime;
//        this.capacity = capacity;
//    }

    // Getter for waitings.
    public List<Booking> getWaitings() {
        return waitings;
    }

    // Setter for waitings.
    public void setWaitings(List<Booking> waitings) {
        this.waitings = waitings;
    }

    // Getter for bookings.
    public List<Booking> getBookings() {
        return bookings;
    }

    // Setter for bookings.
    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    // Getter for starttime.
    public LocalDateTime getStarttime() {
        return starttime;
    }

    // Setter for starttime.
    public void setStarttime(LocalDateTime starttime) {
        this.starttime = starttime;
    }

    // Getter for endtime.
    public LocalDateTime getEndtime() {
        return endtime;
    }

    // Setter for endtime.
    public void setEndtime(LocalDateTime endtime) {
        this.endtime = endtime;
    }

    // Getter for capacity.
    public int getCapacity() {
        return capacity;
    }

    // Setter for capacity.
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    // Getter for slotID.
    public String getSlotID() {
        return slotID;
    }

    // Setter for slotID.
    public void setSlotID(String slotID) {
        this.slotID = slotID;
    }

    // Method to reduce the capacity of the slot by 1.
    public void reduceCapacity() {
        if (capacity > 0) {
            capacity--;
        }
    }

    // Method to increase the capacity of the slot by 1.
    public void increaseCapacity() {
        capacity++;
    }
}
