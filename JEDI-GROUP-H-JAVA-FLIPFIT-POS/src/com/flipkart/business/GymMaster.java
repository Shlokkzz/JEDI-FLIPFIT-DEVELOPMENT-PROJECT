package com.flipkart.business;

public class GymMaster {
	
	GymDaoImpl GymDaoObj=new GymDaoImpl();
	
	List<Gym> getAllGyms()
	{
		return GymDaoObj.getAllGyms();
		
	}

}
