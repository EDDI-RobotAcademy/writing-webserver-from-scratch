package com.eddicorp.application.controller.user;

import com.eddicorp.application.controller.Controller;
import com.eddicorp.application.service.user.UserService;
import com.eddicorp.application.service.user.UserServiceImpl;
import com.eddicorp.http.request.BodyParser;
import com.eddicorp.http.request.HttpRequest;
import com.eddicorp.http.response.HttpResponse;
import com.eddicorp.http.response.HttpStatus;

import java.io.IOException;
import java.util.Map;

public class SignUpUserController implements Controller {
    private final UserService userService = new UserServiceImpl();

    @Override
    public void handle(HttpRequest request, HttpResponse response) throws IOException {
        BodyParser bodyParser = new BodyParser(request.getBody());
        Map<String, String> mappedBody = bodyParser.mappedBody;
        final String username = mappedBody.get("username");
        final String password = mappedBody.get("password");
        userService.signUp(username, password);
        response.setHeader("Location", "/login.html");
        response.setHttpStatus(HttpStatus.FOUND);
        response.setBody(null);

        response.flush();
    }
}
