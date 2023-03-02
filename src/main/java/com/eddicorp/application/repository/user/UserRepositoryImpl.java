package com.eddicorp.application.repository.user;

import com.eddicorp.application.service.user.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepositoryImpl implements UserRepository{
    private final Map<String, User> userDb = new HashMap<>();
    @Override
    public String signUp(User user) {
        String username = user.getUsername();
        userDb.put(username,user);
        System.out.println(userDb.get(username));
        if(userDb.get(username) != null) return "OK";
        else return "ERROR";
    }

    @Override
    public User findByUsername(String username) {
        return userDb.get(username);
    }
}
