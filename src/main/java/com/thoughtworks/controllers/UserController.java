package com.thoughtworks.controllers;

import com.thoughtworks.entities.User;
import com.thoughtworks.services.UserService;
import com.thoughtworks.services.UserServiceI;

public class UserController {
    private UserServiceI userService = new UserService();

    public boolean userRegister(User user) {
        return userService.userRegister(user);
    }
    public boolean userLogin(String name, String password) {
        return userService.userLogin(name, password);
    }
}
