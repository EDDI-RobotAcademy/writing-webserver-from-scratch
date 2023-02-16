package com.eddicorp.quiz.week1.server;

import com.eddicorp.quiz.week1.application.controller.RootController;
import com.eddicorp.quiz.week1.http.request.HttpRequest;
import com.eddicorp.quiz.week1.http.response.HttpResponse;

import java.io.InputStream;
import java.io.OutputStream;

public class RequestHandler {

    private final RootController rootController = new RootController();

    public void handle(final InputStream inputStream, OutputStream outputStream) throws Throwable {
        try {
            final HttpRequest httpRequest = new HttpRequest(inputStream);
            final HttpResponse httpResponse = new HttpResponse(outputStream);
            rootController.handle(httpRequest, httpResponse);
        } catch (Throwable e) {
            throw new Throwable(e);
        }
    }
}
