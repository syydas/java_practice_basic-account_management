package com.thoughtworks.services;

import com.thoughtworks.entities.User;

public interface UserServiceI {
    Boolean userRegister(User user);
    Boolean userLogin(String name, String password);
    User isUserExist(String name);
    boolean isNameRight(String name);
    boolean isPhoneNumberRight(String phoneNumber);
    boolean isEmailRight(String email);
    boolean isPasswordRight(String password);
    void updateErrorNumber(String name, int errorNumber) ;
    void updateStatus(String name, String status) ;
}
