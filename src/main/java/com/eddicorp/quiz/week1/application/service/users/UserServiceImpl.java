package com.eddicorp.quiz.week1.application.service.users;

import com.eddicorp.quiz.week1.application.repository.users.UserRepository;
import com.eddicorp.quiz.week1.application.repository.users.UserRepositoryImpl;

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
