package com.flipkart.dao;

public interface AdminDao {

	public void approveGym(String gymId);
	
	public void  approveGymOwner(String userId);

	public void  viewGyms();
	

	public void  viewPendingGymOwners();

	public void  viewPendingGyms();

}
