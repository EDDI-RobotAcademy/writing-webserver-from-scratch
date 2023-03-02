package com.eddicorp.application.controller;

import com.eddicorp.application.service.user.User;
import com.eddicorp.application.service.user.UserService;
import com.eddicorp.application.service.user.UserServiceImpl;

public class UserController {
    private UserService userService = new UserServiceImpl();
    private String userName;
    private String password;

    public UserController(){

    }

    public String createUser(String body) {
        String[] separateBody = body.split("&");
        String[] SeparateUserName = separateBody[0].split("=");
        String[] separatePassword = separateBody[1].split("=");
        this.userName = SeparateUserName[1].trim();
        this.password = separatePassword[1].trim();
        User user = new User(userName,password);
       return userService.signUp(user);
    }


    public String getUserData() {
        String userData = null;
        User user =  userService.findByUsername(this.userName);
        return user.toString();
    }

//    public String login(){
//        User user =  userService.findByUsername(this.userName);
//        if(){}
//    }
}
