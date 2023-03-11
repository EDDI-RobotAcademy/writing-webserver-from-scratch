package com.eddicorp.application.controller.post;

import com.eddicorp.application.controller.Controller;
import com.eddicorp.application.service.post.PostService;
import com.eddicorp.application.service.post.PostServiceImpl;
import com.eddicorp.http.request.BodyParser;
import com.eddicorp.http.request.HttpRequest;
import com.eddicorp.http.response.HttpResponse;
import com.eddicorp.http.response.HttpStatus;

import java.io.IOException;
import java.util.Map;

public class CreatePostController implements Controller {
    private final PostService postService = new PostServiceImpl();
    @Override
    public void handle(HttpRequest request, HttpResponse response) throws IOException {
        BodyParser bodyParser = new BodyParser(request.getBody());
        Map<String, String> mappedBody = bodyParser.mappedBody;
        final String title = mappedBody.get("title");
        final String content = mappedBody.get("content");
        postService.createPost("123",title, content);

        response.setHeader("Location", "/");
        response.setHttpStatus(HttpStatus.FOUND);

        response.flush();
    }
}
