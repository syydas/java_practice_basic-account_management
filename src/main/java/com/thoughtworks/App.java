package com.thoughtworks;

import com.thoughtworks.controllers.UserController;
import com.thoughtworks.entities.User;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        UserController userController = new UserController();
        boolean isBegin = true;
        while (isBegin) {
            System.out.println("1. 注册");
            System.out.println("2. 登录");
            System.out.println("3. 退出");
            System.out.println("请输入你的选择(1~3)：");
            Scanner input = new Scanner(System.in);
            String userInput;
            String[] userArray;
            User user;
            switch (input.nextInt()) {
                case 1:
                    System.out.println("请输入注册信息(格式：用户名,手机号,邮箱,密码)：");
                    userInput = input.next();
                    userArray = userInput.split(",");
                    user = new User(userArray[0], userArray[1], userArray[2], userArray[3]);
                    System.out.println(userController.userRegister(user));
                    break;
                case 2:
                    System.out.println("请输入用户名和密码(格式：用户名,密码)：");
                    userInput = input.next();
                    userArray = userInput.split(",");
                    user = new User(userArray[0], null, null, userArray[1]);
                    isBegin = !userController.userLogin(user.getName(), user.getPassword());
                    break;
                case 3:
                    isBegin = false;
                    break;
                default:
                    System.out.println("请重新输入你的选择(1~3)");
            }
        }
    }
}

