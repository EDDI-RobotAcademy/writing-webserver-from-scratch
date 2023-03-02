package com.eddicorp.application.service.user;

import com.eddicorp.application.repository.user.UserRepository;
import com.eddicorp.application.repository.user.UserRepositoryImpl;

public class UserServiceImpl implements UserService{
//    private final UserRepository userRepository = UserRepositoryImpl.getInstance();

    private UserRepository userRepository =  new UserRepositoryImpl();
    @Override
    public String signUp(User user) {
       return userRepository.signUp(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
