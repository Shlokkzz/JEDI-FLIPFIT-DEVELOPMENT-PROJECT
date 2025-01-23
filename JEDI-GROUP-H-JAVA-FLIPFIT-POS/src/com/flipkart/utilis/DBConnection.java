package com.flipkart.utilis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DBConnection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {  
			// insert a datapoint
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/test_flipfit","root","sai@1803");  
			  
			PreparedStatement stmt=con.prepareStatement("insert into Emp values(?,?)");  
			stmt.setInt(1,101);//1 specifies the first parameter in the query  
			stmt.setString(2,"aseem");  
			  
			int i=stmt.executeUpdate();  
			System.out.println(i+" records inserted");  
			  
			con.close();  
			  
			}catch(Exception e){ System.out.println(e);}  
			  
		}  

	

}
