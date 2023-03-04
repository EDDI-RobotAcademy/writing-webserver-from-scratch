package com.eddicorp.application.controller;

import com.eddicorp.http.HttpRequest;
import com.eddicorp.http.HttpResponse;

public interface Controller {

    void handle(HttpRequest request, HttpResponse response);
}
