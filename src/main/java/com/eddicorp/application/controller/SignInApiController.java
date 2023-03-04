package com.eddicorp.application.controller;

import com.eddicorp.application.service.users.User;
import com.eddicorp.application.service.users.UserService;
import com.eddicorp.application.service.users.UserServiceImpl;
import com.eddicorp.http.HttpStatus;
import com.eddicorp.http.HttpRequest;
import com.eddicorp.http.HttpResponse;
import com.eddicorp.http.HttpSession;
import com.eddicorp.http.ResponseCookie;
import com.eddicorp.http.SessionManager;

public class SignInApiController implements Controller {

    private final UserService userService = new UserServiceImpl();

    @Override
    public void handle(HttpRequest request, HttpResponse response) {
        final String username = request.getParameter("username");
        final String password = request.getParameter("password");
        final User foundUser = userService.findByUsername(username);
        System.out.println("password = " + password);
        System.out.println("foundUser = " + foundUser);
        if (foundUser != null && foundUser.getPassword().equals(password)) {
            final HttpSession session = request.getSession(true);
            session.setAttribute("USER", foundUser);
            final String sessionId = session.getId();
            final ResponseCookie responseCookie = new ResponseCookie(
                    SessionManager.SESSION_KEY_NAME,
                    sessionId,
                    "/",
                    "localhost",
                    60 * 5
            );
            response.setHeader("Set-Cookie", responseCookie.build());
            response.setHeader("Location", "/");
            response.setStatus(HttpStatus.FOUND);
            response.renderRawBody(null);
        }
    }
}
