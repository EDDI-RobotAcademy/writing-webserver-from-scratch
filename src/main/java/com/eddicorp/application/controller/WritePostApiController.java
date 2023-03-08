package com.eddicorp.application.controller;

import com.eddicorp.application.service.post.Post;
import com.eddicorp.application.service.post.PostService;
import com.eddicorp.application.service.post.PostServiceImpl;
import com.eddicorp.http.HttpRequest;
import com.eddicorp.http.HttpResponse;
import com.eddicorp.http.session.HttpSession;

import java.util.List;

public class WritePostApiController implements Controller{
    private final PostService postService = new PostServiceImpl();

    @Override
    public void handle(HttpRequest httpRequest, HttpResponse httpResponse) {
        final String title = httpRequest.getParameter("title");
        final String content = httpRequest.getParameter("content");
        final HttpSession maybeSession = httpRequest.getSession();
    }
}
