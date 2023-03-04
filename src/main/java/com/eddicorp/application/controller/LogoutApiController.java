package com.eddicorp.application.controller;

import com.eddicorp.http.HttpRequest;
import com.eddicorp.http.HttpResponse;
import com.eddicorp.http.HttpStatus;

public class LogoutApiController implements Controller {
    @Override
    public void handle(HttpRequest request, HttpResponse response) {
        request.getSession().invalidate();
        response.setHeader("Location", "/");
        response.setStatus(HttpStatus.FOUND);
        response.renderRawBody(null);
    }
}
