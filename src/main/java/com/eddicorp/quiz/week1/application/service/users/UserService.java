package com.eddicorp.quiz.week1.application.service.users;

public interface UserService {

    void signUp(String username, String password);

    User findByUsername(String username);
}
