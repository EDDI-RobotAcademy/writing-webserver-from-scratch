package com.eddicorp.quiz.week1.application.repository.users;

import com.eddicorp.quiz.week1.application.service.users.User;

public interface UserRepository {

    void signUp(User user);

    User findByUsername(String username);
}
