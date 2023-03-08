package com.eddicorp.application.controller;

import com.eddicorp.http.HttpRequest;
import com.eddicorp.http.HttpResponse;

public interface Controller {
    void handle(HttpRequest httpRequest, HttpResponse httpResponse);
}
