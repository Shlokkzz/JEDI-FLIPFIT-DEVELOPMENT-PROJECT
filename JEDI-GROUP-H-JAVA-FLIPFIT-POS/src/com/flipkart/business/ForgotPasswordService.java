package com.flipkart.business;
import com.flipkart.business.UserService;

public class ForgotPasswordService implements  ForgotPasswordServiceInterface{
    UserService userService;
    public boolean isUser(String username){
        return userService.UsersMap.get(username) != null;
    }
    public void resetPass(String username, String newPass){
        userService.UsersMap.get(username).setPassword(newPass);
    }
}
