package com.flipkart.dao;

import java.util.List;

import com.flipkart.bean.GymCentre;
import com.flipkart.bean.Slot;

public interface GymOwner {

	public  void addGym(GymCentre gym) ;
	
	
	public   void modifySlot(Slot slot, String gymId) ;
	
	public   void deleteSlot(Slot slot, String gymId) ;
	
	public   void addSlot(Slot slot, String gymId) ;
	
	public   List<String> viewSlots(String gymId) ;
	
	public   List<String> viewMyGyms(String gymOwnerId) ;
}
