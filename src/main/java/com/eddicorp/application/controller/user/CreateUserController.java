package com.eddicorp.application.controller.user;

import com.eddicorp.application.controller.Controller;
import com.eddicorp.http.request.HttpRequest;
import com.eddicorp.http.response.HttpResponse;

import java.io.IOException;

public class CreateUserController implements Controller {
    @Override
    public void handle(HttpRequest request, HttpResponse response) throws IOException {
        for (String key : request.getHeaderMap().keySet()) {
            System.out.println("key: " + key +"\n" + "value: " + request.getHeaderMap().get(key));
        }
        System.out.println(request.getBody());
    }
}
