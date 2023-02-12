package com.eddicorp.application.service.users;

public interface UserService {

    void signUp(String username, String password);

    User findByUsername(String username);
}
