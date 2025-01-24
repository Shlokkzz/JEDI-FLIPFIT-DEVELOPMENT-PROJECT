package com.flipkart.business;

import com.flipkart.bean.*;
import com.flipkart.dao.*;
import com.flipkart.utils.sharedState;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.HashMap;

import static com.flipkart.utils.dbutils.getTableCnt;

public class CustomerService implements CustomerServiceInterface{
//	private int cnt = getTableCnt("user");
	public HashMap<String, Customer> customers = new HashMap<String, Customer>();
	UserService userService=new UserService();
	private CustomerDAO customerDAO = new CustomerDAOImpl();
	private CityDAO cityDAO = new CityDAOImpl();


	Scanner scanner = new Scanner(System.in);

	public void createCustomer(String username, String name, String email, String phone, int age,
                                      String password) {
		String id = "0" + sharedState.getCntUsers();
		sharedState.incrementCntUsers();
		Role role = new Role("C", "Customer");
		List<Booking> bookings = new ArrayList<Booking>();
		Customer customer = new Customer(username, name, email, phone, age, password, id, role.getRoleID(), bookings);
		customers.put(id, customer);
		User user = new User(username, password, id, role.getRoleID());
		userService.addUser(user);
		UserDAOImpl userDAO = new UserDAOImpl();
		boolean val1 = userDAO.addUser(user);
		boolean val2 = userDAO.registerCustomer(customer);
		if(val1 && val2){
			
		}
		else System.out.println("Error : Customer creation failed");
	}

	public void showProfile(Customer customer) {
//		Customer customer = customers.get(id);
		if (customer != null) {
			System.out.println("Username: " + customer.getUsername());
			System.out.println("Name: " + customer.getName());
			System.out.println("Email: " + customer.getEmail());
			System.out.println("Phone: " + customer.getPhone());
			System.out.println("Age: " + customer.getAge());
		} else {
			System.out.println("Customer not found.");
		}
	}

	public List<Booking> viewBookings(String userId) {
		return customerDAO.viewBookings(userId);
	}

	public boolean editProfile(Customer customer) {
		return customerDAO.updateProfile(customer);
	}

	public List<GymCenter> getGymCenters(String city) {
		return cityDAO.fetchGymCenters(city);
	}

}
