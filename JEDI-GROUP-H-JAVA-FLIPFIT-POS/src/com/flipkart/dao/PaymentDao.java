package com.flipkart.dao;

public interface PaymentDao {
	public void makePayment(int receiverId, int senderId, int amount);
}

