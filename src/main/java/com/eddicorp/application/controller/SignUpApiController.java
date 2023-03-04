package com.eddicorp.application.controller;

import com.eddicorp.application.service.users.UserService;
import com.eddicorp.application.service.users.UserServiceImpl;
import com.eddicorp.http.HttpStatus;
import com.eddicorp.http.HttpRequest;
import com.eddicorp.http.HttpResponse;

public class SignUpApiController implements Controller {

    private final UserService userService = new UserServiceImpl();

    @Override
    public void handle(HttpRequest request, HttpResponse response) {
        final String username = request.getParameter("username");
        final String password = request.getParameter("password");
        userService.signUp(username, password);
        response.setHeader("Location", "/login.html");
        response.setStatus(HttpStatus.FOUND);
        response.renderRawBody(null);
    }
}
