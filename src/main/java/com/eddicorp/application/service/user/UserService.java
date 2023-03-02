package com.eddicorp.application.service.user;

public interface UserService {
    String signUp(User user);

    User findByUsername(String username);
}
