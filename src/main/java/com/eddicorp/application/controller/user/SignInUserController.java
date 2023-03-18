package com.eddicorp.application.controller.user;

import com.eddicorp.application.controller.Controller;
import com.eddicorp.application.service.user.User;
import com.eddicorp.application.service.user.UserService;
import com.eddicorp.application.service.user.UserServiceImpl;
import com.eddicorp.http.request.BodyParser;
import com.eddicorp.http.request.HttpRequest;
import com.eddicorp.http.response.HttpResponse;
import com.eddicorp.http.response.HttpStatus;
import com.eddicorp.http.response.ResponseCookie;
import com.eddicorp.http.session.HttpSession;
import com.eddicorp.http.session.SessionManager;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

public class SignInUserController implements Controller {
    private final UserService userService = new UserServiceImpl();

    @Override
    public void handle(HttpRequest request, HttpResponse response) throws IOException {
        BodyParser bodyParser = new BodyParser(request.getBody());
        Map<String, String> mappedBody = bodyParser.getMappedBody();
        final String username = mappedBody.get("username");
        final String password = mappedBody.get("password");
        User maybeUser = userService.findByUsername(username);
        if (maybeUser != null && Objects.equals(maybeUser.getPassword(), password)) {
            final HttpSession httpSession = request.getSession(true);
            httpSession.setAttribute("USER", maybeUser);
            final ResponseCookie sessionResponseCookie = new ResponseCookie(
                    SessionManager.SESSION_KEY_NAME, httpSession.getId(), "/", "localhost", 60 * 3
            );
            response.setHeader("Location", "/");
            response.setHeader("Set-Cookie", sessionResponseCookie.build());
            response.setHttpStatus(HttpStatus.FOUND);
            response.setBody(null);
            response.flush();
            return;
        }
        response.setHeader("Location", "/login-fail.html");
        response.setHttpStatus(HttpStatus.FOUND);
        response.flush();
    }
}
