package com.eddicorp.application.service.user;

public interface UserService {
    void signUp(String username, String password);

    User findByUsername(String username);
}
