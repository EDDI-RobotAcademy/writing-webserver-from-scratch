package com.eddicorp.application.repository.user;


import com.eddicorp.application.service.user.User;

public interface UserRepository {

    void signUp(User user);

    User findByUsername(String username);
}