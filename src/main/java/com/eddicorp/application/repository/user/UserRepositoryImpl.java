package com.eddicorp.application.repository.user;

import com.eddicorp.application.service.user.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepositoryImpl implements UserRepository {

    private static final UserRepository INSTANCE = new UserRepositoryImpl();

    public static UserRepository getInstance() {
        return INSTANCE;
    }

    private UserRepositoryImpl() {
    }

    private final Map<String, User> userDb = new HashMap<>();

    @Override
    public void signUp(User user) {
        userDb.put(user.getUsername(), user);
    }

    @Override
    public User findByUsername(String username) {
        return userDb.get(username);
    }
}
