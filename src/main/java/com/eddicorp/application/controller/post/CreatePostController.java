package com.eddicorp.application.controller.post;

import com.eddicorp.application.controller.Controller;
import com.eddicorp.http.request.HttpRequest;
import com.eddicorp.http.response.HttpResponse;

import java.io.IOException;

public class CreatePostController implements Controller {
    @Override
    public void handle(HttpRequest request, HttpResponse response) throws IOException {
        System.out.println("Post controller running");
    }
}