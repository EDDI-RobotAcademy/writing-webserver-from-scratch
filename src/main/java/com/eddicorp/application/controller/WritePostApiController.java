package com.eddicorp.application.controller;

import com.eddicorp.application.service.posts.PostService;
import com.eddicorp.application.service.posts.PostServiceImpl;
import com.eddicorp.application.service.users.User;
import com.eddicorp.http.HttpSession;
import com.eddicorp.http.HttpStatus;
import com.eddicorp.http.HttpRequest;
import com.eddicorp.http.HttpResponse;

public class WritePostApiController implements Controller {

    private final PostService postService = new PostServiceImpl();

    @Override
    public void handle(HttpRequest request, HttpResponse response) {
        final String title = request.getParameter("title");
        final String content = request.getParameter("content");
        final HttpSession maybeSession = request.getSession();
        String username;
        if (maybeSession == null) {
            username = "익명";
        } else {
            final User user = (User) maybeSession.getAttribute("USER");
            if (user != null && maybeSession.getAttribute("USER") != null) {
                username = user.getUsername();
            } else {
                username = "익명";
            }
        }
        postService.write(username, title, content);
        response.setHeader("Location", "/");
        response.setStatus(HttpStatus.FOUND);
        response.renderRawBody(null);
    }
}
