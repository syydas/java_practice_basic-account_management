package com.thoughtworks.services;

import com.thoughtworks.entities.User;
import com.thoughtworks.repositories.UserRepository;
import com.thoughtworks.repositories.UserRepositoryI;

public class UserService implements UserServiceI {
    private UserRepositoryI userRepository = new UserRepository();


    @Override
    public Boolean userRegister(User user) {
        String checkInfo = checkRegisterInfo(user);
        switch (checkInfo) {
            case "register successful":
                if (userRepository.userRegister(user)) {
                    System.out.println((String.format("%s，恭喜你注册成功！", user.getName())));
                    return true;
                } else {
                    System.out.println("注册失败");
                    return false;
                }
            case "username wrong":
                System.out.println(("用户名不合法\n请输入合法的注册信息："));
                return false;
            case "telephone wrong":
                System.out.println(("手机号不合法\n请输入合法的注册信息："));
                return false;
            case "email wrong":
                System.out.println(("邮箱不合法\n请输入合法的注册信息："));
                return false;
            case "password wrong":
                System.out.println(("密码不合法\n请输入合法的注册信息："));
                return false;
            default:
                return null;
        }
    }

    public String checkRegisterInfo(User user) {
        if (!isNameRight(user.getName())) {
            return "username wrong";
        } else if (!isPhoneNumberRight(user.getPhoneNumber())) {
            return "telephone wrong";
        } else if (!isEmailRight(user.getEmail())) {
            return "email wrong";
        } else if (!isPasswordRight(user.getPassword())) {
            return "password wrong";
        } else {
            return "register successful";
        }
    }
    @Override
    public Boolean userLogin(String name, String password) {
        if (isNameRight(name) && isPasswordRight(password)) {
            User user = isUserExist(name);
            if (user.getName() == null) {
                System.out.println("密码或用户名错误\n请重新输入用户名和密码：");
                return false;
            } else {
                if("locked".equals(user.getStatus())) {
                    System.out.println("您已3次输错密码，账号被锁定");
                    return false;
                } else {
                    User user1 = userRepository.userLogin(name, password);
                    if (user1.getName() == null) {
                        if (user.getErrorNumber() == 2) {
                            updateErrorNumber(user.getName(), 0);
                            updateStatus(user.getName(), "locked");
                            System.out.println("您已3次输错密码，账号被锁定");
                            return false;
                        } else {
                            updateErrorNumber(name, user.getErrorNumber() + 1);
                            System.out.println("密码或用户名错误\n请重新输入用户名和密码：");
                            return false;
                        }
                    } else {
                        System.out.println(user.getName() + "，欢迎回来！\n您的手机号是" + user.getPhoneNumber() + "，邮箱是" + user.getEmail());
                        return true;
                    }
                }
            }
        } else {
            System.out.println("格式错误\n请按正确格式输入用户名和密码：");
            return false;
        }
    }

    @Override
    public User isUserExist(String name) {
       return userRepository.isUserExist(name);
    }

    @Override
    public boolean isNameRight(String name) {
        return name.length() > 2 && name.length() < 10;
    }

    @Override
    public boolean isPhoneNumberRight(String phoneNumber) {
        return (11 == phoneNumber.length()) && (phoneNumber.startsWith("1"));
    }

    @Override
    public boolean isEmailRight(String email) {
        return email.contains("@");
    }

    @Override
    public boolean isPasswordRight(String password) {
        return password.matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$");
    }

    @Override
    public void updateErrorNumber(String name, int errorNumber) {
        userRepository.updateErrorNumber(name, errorNumber);
    }

    @Override
    public void updateStatus(String name, String status) {
        userRepository.updateStatus(name, status);
    }
}
