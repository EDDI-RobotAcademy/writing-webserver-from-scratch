package com.eddicorp.application.service.user;

import com.eddicorp.application.repository.user.UserRepository;
import com.eddicorp.application.repository.user.UserRepositoryImpl;

public class UserServiceImpl implements UserService{

    private final UserRepository userRepository = UserRepositoryImpl.getInstance();
    @Override
    public void signUp(String username, String password) {
        final User newUser = new User(username, password);
        userRepository.signUp(newUser);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
