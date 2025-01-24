package com.flipkart.client;

import com.flipkart.bean.*;
import com.flipkart.business.BookingService;
import com.flipkart.business.CustomerService;
import com.flipkart.business.GymOwnerService;
import com.flipkart.business.UserService;
import com.flipkart.dao.CityDAO;
import com.flipkart.dao.CityDAOImpl;
import com.flipkart.dao.CustomerDAO;
import com.flipkart.dao.CustomerDAOImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * This class represents the menu interface for customers to manage their profiles, bookings,
 * and other customer-related activities in the Flipfit system.
 */
public class CustomerFlipfitMenu {

    private Scanner scanner = new Scanner(System.in);

    // UserService instance to handle user-related operations
    private UserService userServiceInterface;

    // CustomerService instance to handle customer-related operations
    private CustomerService customerService = new CustomerService();
    private GymOwnerService gymOwnerService = new GymOwnerService();
    private BookingService bookingService = new BookingService();
    private CityDAO cityDAO = new CityDAOImpl();


    /**
     * Constructor initializes the scanner and user service interface.
     *
     * @param scanner Scanner instance for user input
     */
    public CustomerFlipfitMenu(Scanner scanner) {
        this.scanner = scanner;
        this.userServiceInterface = new UserService();
    }

    /**
     * Registers a new customer by taking input details from the user.
     *
     * @param scanner Scanner instance for user input
     */
    public void registerCustomer(Scanner scanner) {
        System.out.println("Enter your Username");
        String username = scanner.nextLine();
        System.out.println("Enter your Password");
        String password = scanner.nextLine();
        System.out.println("Enter your Name");
        String name = scanner.nextLine();
        System.out.println("Enter your Phone");
        String phone = scanner.nextLine();
        System.out.println("Enter your Email");
        String mail = scanner.nextLine();
        System.out.println("Enter your Age");
        int age = Integer.parseInt(scanner.nextLine());

        // Create a new customer using the provided details
        customerService.createCustomer(username, name, mail, phone, age, password);
    }

    /**
     * Displays the customer menu and handles customer choices.
     *
     * @param customer the logged-in customer user
     */

    public void showMenu(Customer customer) {
        int userChoice = -1;

        // Loop until the customer chooses to exit
        while (userChoice != 7) {
            // Display customer menu options
            System.out.println("Customer Menu:");
            System.out.println("1. View Profile");
            System.out.println("2. Edit Profile");
            System.out.println("3. Book Slot");
            System.out.println("4. View Bookings");
            System.out.println("5. Cancel Booking");
            System.out.println("6. Change Password");
            System.out.println("7. Logout");
            System.out.print("Enter your choice: ");
            userChoice = scanner.nextInt();
            scanner.nextLine(); // consume the newline

            // Handle customer's choice
            switch (userChoice) {
                case 1:
                    customerService.showProfile(customer);
                    break;
                case 2:
                    editProfile(customer);
                    break;
                case 3:
                    addbookings(customer);
                    break;
                case 4:
                    viewBookings(customer.getUserid());
                    break;
                case 5:
                    cancelbookings(customer.getUserid());
                    break;
                case 6:
                    changePassword(customer);
                    break;
                case 7:
                    System.out.println("Logging Out!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    public void addbookings(Customer customer) {
        List<City> cities = cityDAO.getAllCities();
        AtomicInteger itr = new AtomicInteger(1);
        cities.forEach(city -> {
            System.out.println(itr.getAndIncrement() + ". " + city.getCityName());
        });
        System.out.println("Enter City: ");
        String city=scanner.nextLine();
        int c=1;
        List<GymCenter> gymCenters = customerService.getGymCenters(city.toLowerCase());
        for(GymCenter gymCenter :gymCenters){
            System.out.println(c + ". " + gymCenter.getGymName());
            c++;
        }

        System.out.println("Enter Gym Name: ");
        String gn=scanner.nextLine();
        GymCenter gymCenter_sel = gymCenters.stream()
                .filter(gc -> gc.getGymName().equalsIgnoreCase(gn))
                .findFirst()
                .orElse(null);
        if(gymCenter_sel != null){
            List<Slot> slots = gymCenter_sel.getSlots();
            if (slots.isEmpty()) {
                System.out.println("No slots available for this gym center.");
            }
            else{
                System.out.println("Available slots:");
                for (int i = 0; i < slots.size(); i++) {
                    Slot slot = slots.get(i);
                    System.out.printf("%d. Start time: %s, End time: %s, Capacity: %d%n",
                            i + 1, slot.getStarttime(), slot.getEndtime(), slot.getCapacity());
                }

                System.out.print("Choose a slot (enter the number): ");
                int choice = scanner.nextInt();
                Slot slot = slots.get(choice-1);
                if(bookingService.bookSlot(customer.getUserid(), gymCenter_sel, slot)){
                    System.out.println("Booking successful!");
                }
                else {
                    System.out.println("Booking failed!");
                }
            }

//            else{
//                System.out.println("Slot is already full.");
//                CustomerService customerService=new CustomerService();
////                Customer=customerService.customers.get(user.getUserid());
//                int f=0;
//                pair<Booking, Boolean> oldbk=null;
//                for(pair<Booking, Boolean> bk:customer.getBookings()){
//                    if(bk.getFirst().getStarttime()==st){
//                        oldbk=bk;
//                        if(bk.getSecond()){
//                            f=1;
//                        }
//                    }
//                }
//                if(f==1){
//                    System.out.println("You already have confirmed booking for this time slot "+st+" at "+oldbk.getFirst().getGymCenter().getGymName()+".");
//                    System.out.println("Note: If you wish to book slot you will loose previous confirmed booking.");
//                }
//                System.out.println("Do you still want to book slot in waitlist?");
//                System.out.println("Enter choice: ");
//                System.out.println("1. YES");
//                System.out.println("2. NO");
//                String ch=scanner.nextLine();
//                switch (ch){
//                    case "1":
//                        List<Slot> slots=gymOwnerService.searchcitygc(gn, city).getSlots();
//                        Slot slot = null;
//                        for (Slot sl:slots){
//                            if(sl.getStarttime()==st){
//                                slot=sl;
//                                break;
//                            }
//                        }
//                        String bkid="0"+slot.getBookings().size()+1;
//                        Booking newbooking = new Booking(customer.getUserid(), bkid, gymOwnerService.searchcitygc(gn, city), st);
//                        bookingService.cancelslotbooking(customer.getUserid(), oldbk.getFirst().getGymCenter(), st);
//                        slot.getWaitings().add(newbooking);
//                        customer.getBookings().add(new pair<>(newbooking,false));
//                        if(f==1){
//                            System.out.println("Previous overlapping booking is cancelled.");
//                        }
//                        System.out.println("Booking on the waitlist at "+gymOwnerService.searchcitygc(gn, city).getGymName()+" "+st+".");
//                        break;
//                    case "2":
//                        break;
//                    default:
//                        System.out.println("Invalid choice.");
//                }
//            }
        }
        else{
            System.out.println("Invalid Gym Name.");
        }
    }

    public void viewBookings(String userId) {
        List<Booking> bookings = customerService.viewBookings(userId);

        if (bookings.isEmpty()) {
            System.out.println("No bookings found for user: " + userId);
        } else {
            System.out.println("Bookings for user: " + userId);
            for (Booking booking : bookings) {
                System.out.println("Booking Gym: " + booking.getGymName());
                System.out.println("Slot Time: " + booking.getSlot().getStarttime() + " - " + booking.getSlot().getEndtime());
                System.out.println();
            }
        }
    }

    public void cancelbookings(String userId) {
//        System.out.println("Enter City: ");
//        String city=scanner.nextLine();
//        int c=1;
//        List<GymCenter> gc=gymOwnerService.getCityGymcenters().get(city);
//        for(GymCenter gcc :gc){
//            System.out.println(c + ". " + gcc.getGymName());
//            c++;
//        }
//        System.out.println("Enter Gym Name: ");
//        String gn=scanner.nextLine();
//        if(gymOwnerService.searchcitygc(gn, city)!=null){
//            System.out.println("Enter year(yyyy): ");
//            int year=scanner.nextInt();
//            System.out.println("Enter month(mm): ");
//            int month=scanner.nextInt();
//            System.out.println("Enter date(dd): ");
//            int date=scanner.nextInt();
//            System.out.println("Enter hour according to 24hrs clock: ");
//            int hr=scanner.nextInt();
//            LocalDateTime st=LocalDateTime.of(year, month, date, hr, 0, 0);
//            BookingService bookingService=new BookingService();
//            bookingService.cancelslotbooking(userId, gymOwnerService.searchcitygc(gn, city), st);
//            System.out.println("Booking cancelled successfully.");
        List<Booking> bookings = customerService.viewBookings(userId);
        if (bookings.isEmpty()) {
            System.out.println("No bookings found!!!");
        }
        else {
            System.out.println("Bookings:\n");
            int itr=1;
            for (Booking booking : bookings) {
                System.out.println(itr + ". " + booking.getGymName() + ": " + booking.getBookingDate() + " Duration: 1hr\n");
                itr++;
            }
            System.out.println("Choose a booking you wish to cancel(enter the number): ");
            int choice = scanner.nextInt();
            Booking booking = bookings.get(choice-1);
            if(bookingService.cancelSlotBooking(userId, booking.getSlotID())){
                System.out.println("Slot Cancelled");
            } else {
                System.out.println("Cancellation unsuccessful!!");
            }
        }
    }

    public void editProfile(Customer customer) {
        if (customer != null) {
            boolean updating = true;
            while (updating) {
                System.out.println("Choose the field to update:");
                System.out.println("1. Name");
                System.out.println("2. Email");
                System.out.println("3. Phone");
                System.out.println("4. Age");
                System.out.println("5. Exit");

                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        System.out.println("Enter new name:");
                        customer.setName(scanner.nextLine());
                        break;
                    case 2:
                        System.out.println("Enter new email:");
                        customer.setEmail(scanner.nextLine());
                        break;
                    case 3:
                        System.out.println("Enter new phone:");
                        customer.setPhone(scanner.nextLine());
                        break;
                    case 4:
                        System.out.println("Enter new age:");
                        customer.setAge(Integer.parseInt(scanner.nextLine()));
                        break;
                    case 5:
                        updating = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
//            Make an update function in DAO for customer table entry
            if(customerService.editProfile(customer)){
                System.out.println("Customer profile updated.");
            }
        } else {
            System.out.println("Customer not found.");
        }
    }



    /**
     * Allows the customer to change their password.
     *
     * @param user the logged-in customer user
     */
    public void changePassword(User user) {
        System.out.println("Enter your Old Password");
        String password = scanner.nextLine();
        boolean flag = userServiceInterface.validatePassword(user, password);
        if (flag) {
            System.out.println("Enter your New Password");
            String newPassword = scanner.nextLine();
            System.out.println("Confirm your Password");
            String confirmPassword = scanner.nextLine();
            userServiceInterface.confirmPassword(user, newPassword, confirmPassword);
        } else {
            System.out.println("Wrong Old Password.");
        }
    }
}
