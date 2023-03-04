package com.eddicorp.application.repository.users;

import com.eddicorp.application.service.users.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepositoryImpl implements UserRepository {

    private static final UserRepository INSTANCE = new UserRepositoryImpl();

    public static UserRepository getInstance() {
        return INSTANCE;
    }

    private final Map<String, User> userDb = new HashMap<>();

    private UserRepositoryImpl() {
    }

    @Override
    public void signUp(User user) {
        userDb.put(user.getUsername(), user);
    }

    @Override
    public User findByUsername(String username) {
        return userDb.get(username);
    }
}
