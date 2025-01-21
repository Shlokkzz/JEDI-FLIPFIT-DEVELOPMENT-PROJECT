/**
 * 
 */
package com.flipkart.client;

import com.flipkart.business.CustomerOperations;

/**
 * 
 */
public class CustomerClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CustomerOperations operation = new CustomerOperations();
		operation.createCustomer();
		operation.listCustomer();
		
		System.out.println(operation.updateCustomer(101));
		System.out.println(operation.deleteCustomer(101));
	}

}
