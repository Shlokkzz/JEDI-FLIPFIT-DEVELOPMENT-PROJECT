package com.flipkart.dao;

import  com.flipkart.jdbc.DemoJdbc;
import  com.flipkart.bean.GymOwner;
import  com.flipkart.bean.GymCentre;
import  com.flipkart.constants.SQLConstants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDaoImpl {
	
	@Override
	public void approveGym(String gymId) {
		try {
			con=DemoJdbc.connect();
			System.out.println("Fetching gym centres");
			
			String sqlQuery = "UPDATE GymCenter SET status = "approved" WHERE gym_id = ?";
			statement=con.prepareStatement(sqlQuery);
			statement.setString(1,gymId);
			statement.executeUpdate();
			
			List<GymCentre> approvedGyms = viewGyms(); 
	        for (GymCentre gym : approvedGyms) {
	            System.out.println("Gym ID: " + gym.getGymId() + ", Name: " + gym.getName() + ", Location: " + gym.getLocation());
	        }

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
			
		}
	
	@Override
	public void  approveGymOwner(String userId) {
	 try {
		 con=DemoJdbc.connect();
         System.out.println("Fetching gyms owners");

         String sqlQuery = "UPDATE User SET status = "approved" WHERE gym_id = ?";
         statement = con.prepareStatement(sqlQuery);
         statement.setString(1, ownerId);
         statement.executeUpdate();
         

     } catch (SQLException se) {
         se.printStackTrace();
     } catch (Exception e) {
         e.printStackTrace();
     }
	}
	
	@Overrideg
	public List<GymCentre> viewGyms()
	{
		 try {
			    con=DemoJdbc.connect();
	            System.out.println("Fetching gyms centres");
	            
	            String sqlQuery = "SELECT * FROM GymCenters WHERE status="approved"";
	            statement = con.prepareStatement(sqlQuery);
	            ResultSet rs = statement.executeQuery();

	            List<GymCentre> gymCentres = new ArrayList<>();
	            while(rs.next()) {
	                Gym gymCentre = new GymCentre(
	                        rs.getString("gymId"),
	                        rs.getString("name"),
	                        rs.getString("location"),
	                        rs.getString("gymOwnerId"),
	                );
	                gymCentres.add(gymCentre);
	            }
	            return gymCentres;
	        }
	        catch (SQLException se)
	        {
	            se.printStackTrace();
	            return null;
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	            return null;
	        }
	}
	
	@Override
	public List<GymOwner> viewPendingGymOwners() {
		try {
		    con=DemoJdbc.connect();
            System.out.println("Fetching pending gymOwner approval");
            
            String sqlQuery = "SELECT * FROM User WHERE status = "pending"";
            statement = con.prepareStatement(sqlQuery);
            ResultSet rs = statement.executeQuery();

            List<GymOwner> gymOwners  = new ArrayList<>();
            while(rs.next()) {
                GymOwner gymOwner = new GymOwner(
                        rs.getString("name"),
                        rs.getString("email"),
                );
                gymOwners.add(gymOwner);
            }
            return gymOwners;
        }
        catch (SQLException se)
        {
            se.printStackTrace();
            return null;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
	}
	
	@Override
	public List<GymCentre>  viewPendingGyms() {
		try {
		    con=DemoJdbc.connect();
            System.out.println("Fetching pending gym approval");
            
            String sqlQuery = "SELECT * FROM GymCenter WHERE status = "pending"";
            statement = con.prepareStatement(sqlQuery);
            ResultSet rs = statement.executeQuery();

            List<GymCentre> gymCentres  = new ArrayList<>();
            while(rs.next()) {
                GymCentre gymCentre = new GymCentre(
                	    rs.getString("gymId"),
                        rs.getString("name"),
                        rs.getString("location"),
                        rs.getString("gymOwnerId"),);
                gymCentres.add(gymCentre);
            }
            return gymCentres;
        }
        catch (SQLException se)
        {
            se.printStackTrace();
            return null;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
	}
}
