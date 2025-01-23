package com.flipkart.business;

import com.flipkart.dao.UserDaoImpl;

public class UserOperations {
	
	UserDaoImpl userDaoObj=new UserDaoImpl();
	
	

	public Boolean verifyUser(String username, String password) {
		
		System.out.println("User verified\n");
		//return s1.equals(s2)&& s3.equals(s4);
		return false;
		
	}
	public Boolean updateUser(String userId, int choice, String updateValue) {
		
		return userDaoObj.updateUser(userId, choice, updateValue);
		
	
	}
	
	public Boolean deleteUser(String userId)
	{
		return  userDaoObj.deleteUser(userId);
	}
	
	public Boolean createUser(String username ,String email,String password ,String role)
	{
		return userDaoObj.createUser(username, email, password, role);
	}
	
}
