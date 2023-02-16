package com.eddicorp.quiz.week1.application.controller;

import com.eddicorp.quiz.week1.http.request.HttpRequest;
import com.eddicorp.quiz.week1.http.response.HttpResponse;

public interface Controller {

    void handle(HttpRequest request, HttpResponse response);
}
