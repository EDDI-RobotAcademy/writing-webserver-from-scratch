package com.eddicorp.server;

import com.eddicorp.application.controller.RootController;
import com.eddicorp.http.HttpRequest;
import com.eddicorp.http.HttpResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class RequestHandler {

    private final RootController  rootController = new RootController();
    public void handle(InputStream inputStream, OutputStream outputStream) {
        try {
            final HttpRequest httpRequest = new HttpRequest(inputStream);
            final HttpResponse httpResponse = new HttpResponse(outputStream);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
