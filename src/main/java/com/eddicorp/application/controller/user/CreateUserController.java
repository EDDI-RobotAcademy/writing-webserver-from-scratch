package com.eddicorp.application.controller.user;

import com.eddicorp.application.controller.Controller;
import com.eddicorp.http.request.HttpRequest;
import com.eddicorp.http.response.HttpResponse;

import java.io.IOException;

public class CreateUserController implements Controller {
    @Override
    public void handle(HttpRequest request, HttpResponse response) throws IOException {
        System.out.println("User create controller running");
    }
}
