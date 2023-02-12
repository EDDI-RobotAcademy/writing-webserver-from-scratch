package com.eddicorp.application.controller;

import com.eddicorp.http.request.HttpRequest;
import com.eddicorp.http.response.HttpResponse;

import java.nio.file.Paths;

public class StaticFileController implements Controller {

    private static final String STATIC_FILE_PATH = "pages";

    @Override
    public void handle(HttpRequest request, HttpResponse response) {
        final String uri = request.getUri();
        final String pathToLoad = Paths.get(STATIC_FILE_PATH, uri).toString();
    }
}
