package com.eddicorp.application;

import com.eddicorp.http.HttpRequest;
import com.eddicorp.http.HttpResponse;

public class RootController implements Controller{
    private final Controller staticFileController = new StaticFileController();

    @Override
    public void handle(HttpRequest request, HttpResponse response) {
        final String uri = request.getUri();

    }
}
