package com.eddicorp.application.controller;

import com.eddicorp.http.request.HttpRequest;
import com.eddicorp.http.response.HttpResponse;

public interface Controller {

    void handle(HttpRequest request, HttpResponse response);
}
