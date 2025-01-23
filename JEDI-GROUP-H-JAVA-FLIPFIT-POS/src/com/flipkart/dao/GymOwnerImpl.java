package com.flipkart.dao;
import java.util.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;


import com.flipkart.bean.GymCentre;
import com.flipkart.bean.Slot;
import com.flipkart.business.GymOwnerOperations;

public class GymOwnerImpl implements GymOwner {
	
	
	
	
	
	
	public void addGym(GymCentre gym) {
		
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/Flipfit","root","sai@1803");  
			  
		  
		PreparedStatement stmt=con.prepareStatement("insert into GymCenter values(?,?,?,?)");  
		stmt.setString(1,gym.getGymId());//1 specifies the first parameter in the query  
		stmt.setString(2,gym.getName());  
		stmt.setString(3, gym.getLocation());
		stmt.setString(4,gym.getGymOwnerId());
		  
		int i=stmt.executeUpdate();  
		//System.out.println(i+" records inserted"); 
		  
		con.close();  
		  
		}catch(Exception e){ System.out.println(e);}  
		  
		}  
		
		
	}
	
	
	public   void modifySlot(Slot slot, String gymId) {
		


		try {  
		    // Load the MySQL JDBC driver
		    Class.forName("com.mysql.jdbc.Driver");  

		    // Establish connection
		    Connection con = DriverManager.getConnection(  
		        "jdbc:mysql://localhost:3306/Flipfit", "root", "sai@1803");  

		    // Prepare the SQL statement
		    PreparedStatement stmt = con.prepareStatement("insert into Slot values(?,?,?,?,?)");

		    // Set slotId and gymId (strings)
		    stmt.setString(1, slot.getSlotId());  
		    stmt.setString(2, slot.getGymId());  
		    stmt.setInt(4, slot.getSeats());
		    //Time todo
		    
		    
		    LocalDate localDate = slot.getStartTime().toLocalDate();
		    Date sqlDate = new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
		   // stmt.setDate(5, sqlDate); To do time and date
		    
		    
		    // Execute the insert
		    int i = stmt.executeUpdate();  
		    System.out.println(i + " records inserted");

		    // Additional logic for gym operations
	


		    // Close the connection
		    con.close();  

		} catch (Exception e) { 
		    System.out.println(e); 
		}  

		
		
		
	}
	
	public   void deleteSlot(Slot slot, String gymId) {
		try {  
		    // Load the MySQL JDBC driver
		    Class.forName("com.mysql.jdbc.Driver");  

		    // Establish connection
		    Connection con = DriverManager.getConnection(  
		        "jdbc:mysql://localhost:3306/Flipfit", "root", "sai@1803");  

		    PreparedStatement stmt=con.prepareStatement("delete from Slot where id={gymId}");  
		    stmt.setString(1,slot.getSlotId());  
		      
		    int i=stmt.executeUpdate();  
		    System.out.println(i+" records deleted");  

	

		    // Close the connection
		    con.close();  

		} catch (Exception e) { 
		    System.out.println(e); 
		}  
		
	}
	
	public   void addSlot(Slot slot, String gymId) {
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/Flipfit","root","sai@1803");  
			  
		  
		PreparedStatement stmt=con.prepareStatement("insert into Slot values(?,?,?,?,?)");  
		stmt.setString(1,slot.getSlotId());//1 specifies the first parameter in the query  
		stmt.setString(2,slot.getGymId()); 
		
		
		//Time todo
		
		stmt.setInt(4,slot.getSeats());
		//Date todo
		  
		int i=stmt.executeUpdate();  
		//System.out.println(i+" records inserted"); 
		  
		con.close();  
		  
		}catch(Exception e){ System.out.println(e);}  
		  
		}  
			
	
		
	
	public   List<String> viewSlots(String gymId) {

		List<String> slots=new ArrayList<>();
		try{  
			
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection( "jdbc:mysql://localhost:3306/Flipfit","root","sai@1803");  		  
			PreparedStatement stmt=con.prepareStatement("select * from Slots where slotId=?");  
			stmt.setString(1,gymId);//1 specifies the first parameter in the 
			
			ResultSet rs=stmt.executeQuery();  
			while(rs.next()){  
				slots.add(rs.getString(1));  
				slots.add(rs.getString(2));  
			//Time todo	slots.add(rs.getString(3));  
				slots.add(Integer.toString(rs.getInt(4)));  
	//		Data todo	slots.add(rs.getString(1));  
			}  
		
			con.close();  
		  
		}
		catch(Exception e){ System.out.println(e);}  
		  
		 
		return slots;
	}
	
	public  List<String> viewMyGyms(String gymOwnerId) {
		List<String> gyms=new ArrayList<>();
		try{  
			
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection( "jdbc:mysql://localhost:3306/Flipfit","root","sai@1803");  		  
			PreparedStatement stmt=con.prepareStatement("select * from GymCenter where gymOwnerId=?");  
			stmt.setString(1,gymOwnerId);//1 specifies the first parameter in the 
			
			ResultSet rs=stmt.executeQuery();  
			while(rs.next()){  
				gyms.add(rs.getString(1));  
				gyms.add(rs.getString(2));  
				gyms.add(rs.getString(3));  
				gyms.add(rs.getString(4));   
			}  
		
			con.close();  
		  
		}
		catch(Exception e){ System.out.println(e);}  
		  
		 
		return gyms;
		
	}

}
