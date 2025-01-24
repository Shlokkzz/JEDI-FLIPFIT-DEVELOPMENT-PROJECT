package com.flipkart.business;

import com.flipkart.bean.*;
import com.flipkart.dao.CustomerDAO;
import com.flipkart.dao.CustomerDAOImpl;
import com.flipkart.utils.sharedState;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class BookingService {
    Scanner scanner = new Scanner(System.in);
    private CustomerService customerService=new CustomerService();
    private CustomerDAOImpl customerDAO = new CustomerDAOImpl();
//    public void cancelslotbooking(String userid, GymCenter gymCenter, LocalDateTime st){
//        CustomerService customerService=new CustomerService();
//        Customer customer=customerService.customers.get(userid);
//        for(pair<Booking, Boolean> bk:customer.getBookings()){
//            if(bk.getFirst().getStarttime()==st){
//                List<Booking> bookingList=null;
//                Slot slot=null;
//                for(Slot sl: gymCenter.getSlots()){
//                    if(sl.getStarttime()==st){
//                        slot=sl;
//                        break;
//                    }
//                }
//                if(bk.getSecond()){
//                    for(Booking bsl:slot.getBookings()){
//                        if(bsl.getUserID().equals(userid)){
//                            slot.getBookings().remove(bsl);
//                            slot.increaseCapacity();
//                            break;
//                        }
//                    }
//                }
//                else{
//                    for(Booking bsl:slot.getWaitings()){
//                        if(bsl.getUserID().equals(userid)){
//                            slot.getWaitings().remove(bsl);
//                            break;
//                        }
//                    }
//                }
//                customer.getBookings().remove(bk);
//                break;
//            }
//        }
//
//    }

    public boolean cancelSlotBooking(String userID, String slotID) {
        // Cancel the booking
        return customerDAO.cancelBooking(userID, slotID);
    }

    public Boolean bookSlot(String userid, GymCenter gymCenter, Slot slot_sel){
        sharedState.incrementCntBookings();
        String bkid = "0" + sharedState.getCntBookings();

        LocalDateTime bookingDate = slot_sel.getStarttime();
        Booking newbooking = new Booking(userid, bkid, gymCenter.getGymID(), slot_sel.getSlotID(), bookingDate);
        if(slot_sel.getCapacity()>0){
//            If slot_sel already exists under current userID in booking --> CancelThisBooking()
            if(customerDAO.bookingExists(newbooking)){
                if(cancelSlotBooking(newbooking.getUserID(), slot_sel.getSlotID())){
                    System.out.println("Previous overlapping booking is cancelled.");
                } else {
                    System.out.println("Previous overlapping booking cancellation Err.");
                }
            }
            if(customerDAO.addBooking(newbooking)){
                customerDAO.updateCapacity(newbooking.getSlotID(), -1);
                System.out.println("Confirm booking at "+ gymCenter.getGymName()+" "+slot_sel.getStarttime()+".");
                return true;
            }
//            Write waitlist code
            else return false;
        }
        return false;

    }

}
