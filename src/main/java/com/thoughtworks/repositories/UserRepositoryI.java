package com.thoughtworks.repositories;

import com.thoughtworks.entities.User;

import java.util.List;

public interface UserRepositoryI {
    Boolean userRegister(User user);

    User userLogin(String name, String password);

    User isUserExist(String name);

    void updateErrorNumber(String name, int errorNumber) ;

    void updateStatus(String name, String status) ;
}
