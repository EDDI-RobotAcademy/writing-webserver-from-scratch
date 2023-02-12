package com.eddicorp.application.repository.users;

import com.eddicorp.application.service.users.User;

public interface UserRepository {

    void signUp(User user);

    User findByUsername(String username);
}
