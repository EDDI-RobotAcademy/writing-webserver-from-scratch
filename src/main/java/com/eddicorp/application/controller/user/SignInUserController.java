package com.eddicorp.application.controller.user;

import com.eddicorp.application.controller.Controller;
import com.eddicorp.application.service.user.User;
import com.eddicorp.application.service.user.UserService;
import com.eddicorp.application.service.user.UserServiceImpl;
import com.eddicorp.http.request.BodyParser;
import com.eddicorp.http.request.HttpRequest;
import com.eddicorp.http.response.HttpResponse;
import com.eddicorp.http.response.HttpStatus;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

public class SignInUserController implements Controller {
    private final UserService userService = new UserServiceImpl();

    @Override
    public void handle(HttpRequest request, HttpResponse response) throws IOException {
        BodyParser bodyParser = new BodyParser(request.getBody());
        Map<String, String> mappedBody = bodyParser.mappedBody;
        final String username = mappedBody.get("username");
        final String password = mappedBody.get("password");
        User maybeUser = userService.findByUsername(username);
        if (maybeUser != null && Objects.equals(maybeUser.getPassword(), password)) {
            response.setHeader("Location", "/");
            response.setHttpStatus(HttpStatus.FOUND);
            response.setBody(null);

            response.flush();
            return;
        }
        response.setHttpStatus(HttpStatus.NOT_FOUND);
        response.setHeader("Location", "/login-fail.html");
        response.setHttpStatus(HttpStatus.FOUND);
        response.flush();
    }
}
