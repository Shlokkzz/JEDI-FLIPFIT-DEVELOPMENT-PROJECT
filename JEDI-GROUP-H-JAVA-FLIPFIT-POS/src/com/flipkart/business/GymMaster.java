package com.flipkart.business;

public class GymMaster {
	
	GymDaoImpl GymDaoObj=new GymDaoImpl();
	
	List<GymCentre> getAllGyms()
	{
		return GymDaoObj.getAllGyms();
		
	}

}
