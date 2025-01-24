package com.flipkart.bean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// Represents a gym center entity.
public class GymCenter {
    private String gymID;
    private String gymName;
    private String address;
    private String city;
    private String gymOwnerID;
    private List<Slot> slots;

    /* Constructor to initialize a GymCenter object.
     Parameters:
     gymID: Unique ID of the gym center.
     gymName: Name of the gym center.
     address: Address of the gym center.
     city: City where the gym center is located.
     slots: List of slots available at the gym center.
     */
    public GymCenter(String gymID, String gymName, String address, String city, List<Slot> slots,  String gymOwnerID) {
        this.gymID = gymID;
        this.gymName = gymName;
        this.address = address;
        this.city = city;
        this.slots=slots;
        this.gymOwnerID=gymOwnerID;
    }

    /* Method to add a new slot to the gym center.
     Returns a message indicating the success or failure of the operation.
     */
//    public String addSlot(String id, LocalDateTime st, LocalDateTime et, int capacity) {
//        for (Slot sl : slots) {
//            if (sl.getStarttime().equals(st)) {  // Check if a slot with the same start time already exists.
//                return "Slot already exists.";
//            }
//        }
//        // Create a new Slot object and add it to the slots list.
//        Slot slot = new Slot(id, st, et, capacity, new ArrayList<Booking>(), new ArrayList<Booking>());
//        slots.add(slot);
//        return "Slot added successfully.";
//    }

    /* Method to remove a slot from the gym center based on its start time.
     Returns a message indicating the success or failure of the operation.
     */
//    public String removeSlot(LocalDateTime st) {
//        for (Slot sl : slots) {
//            if (sl.getStarttime().equals(st)) {  // Check if a slot with the specified start time exists.
//                slots.remove(sl);               // Remove the slot from the slots list.
//                return "Slot removed successfully.";
//            }
//        }
//        return "Slot doesn't exist to remove.";
//    }

    // Getter for slots.
    public List<Slot> getSlots() {
        return slots;
    }

    // Setter for slots.
    public void setSlots(List<Slot> slots) {
        this.slots = slots;
    }

    // Getter for gymID.
    public String getGymID() {
        return gymID;
    }

    // Setter for gymID.
    public void setGymID(String gymID) {
        this.gymID = gymID;
    }

    // Getter for gymName.
    public String getGymName() {
        return gymName;
    }

    // Setter for gymName.
    public void setGymName(String gymName) {
        this.gymName = gymName;
    }

    // Getter for address.
    public String getAddress() {
        return address;
    }

    // Setter for address.
    public void setAddress(String address) {
        this.address = address;
    }

    // Getter for city.
    public String getCity() {
        return city;
    }
  
    // Getter for Gym Owner ID.
    public String getGymOwnerID() {
        return gymOwnerID;
    }
  
    // Setter for Gym Owner ID.
    public void setGymOwnerID(String gymOwnerID) {
        this.gymOwnerID = gymOwnerID;
    }

    // Setter for city.
    public void setCity(String city) {
        this.city = city;
    }
}
