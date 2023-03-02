package com.eddicorp.application.controller;

import com.eddicorp.http.request.HttpRequest;
import com.eddicorp.http.response.HttpResponse;

import java.io.IOException;

public interface Controller {

    void handle(HttpRequest request, HttpResponse response) throws IOException;
}
