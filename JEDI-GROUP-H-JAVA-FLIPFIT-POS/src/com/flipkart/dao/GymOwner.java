package com.flipkart.dao;

import com.flipkart.bean.GymCentre;
import com.flipkart.bean.Slot;

public interface GymOwner {

	public  void addGym(GymCentre gym) ;
	
	
	public   void modifySlot(Slot slot, String gymId) ;
	
	public   void deleteSlot(Slot slot, String gymId) ;
	
	public   void addSlot(Slot slot, String gymId) ;
	
	public   void viewSlots(String gymId) ;
	
	public   void viewMyGyms() ;
}
