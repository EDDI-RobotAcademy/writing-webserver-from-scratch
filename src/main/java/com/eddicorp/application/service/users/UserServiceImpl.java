package com.eddicorp.application.service.users;

import com.eddicorp.application.repository.users.UserRepository;
import com.eddicorp.application.repository.users.UserRepositoryImpl;

public class UserServiceImpl implements UserService {

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
