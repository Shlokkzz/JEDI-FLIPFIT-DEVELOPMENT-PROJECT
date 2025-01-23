package com.flipkart.dao;



public interface UserDao {
	



public String verifyUser(String username ,String password ,String role );



public Boolean updateUser(String userId , int choice ,String updateValue);

public Boolean deleteUser(String userId);

public Boolean createUser(String username ,String email,String password ,String role);


}