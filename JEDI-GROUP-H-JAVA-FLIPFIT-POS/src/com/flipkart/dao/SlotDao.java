package com.flipkart.dao;
import com.flipkart.bean.Slot;
import java.util.List;

public interface SlotDao {
	
	public List<Slot> getAllSlotsByGym(String gymId);
	

}
