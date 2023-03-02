package com.eddicorp.server;

import com.eddicorp.application.controller.Controller;
import com.eddicorp.application.controller.RootController;
import com.eddicorp.http.request.HttpRequest;
import com.eddicorp.http.response.HttpResponse;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

public class ServerConnectionHandler {
    private final Socket connection;
    private final Controller rootController = new RootController();


    public ServerConnectionHandler(Socket connection) {
        this.connection = connection;
    }

    public void handleHttp() throws Throwable {
        try (
                final InputStream inputStream = connection.getInputStream();
                final OutputStream outputStream = connection.getOutputStream();
        ) {
            final HttpRequest httpRequest = new HttpRequest(inputStream);
            final HttpResponse httpResponse = new HttpResponse(outputStream);
            rootController.handle(httpRequest, httpResponse);
        } catch (Throwable throwable) {
            System.out.println(Arrays.toString(throwable.getStackTrace()));
            throw new Throwable(throwable);
        }
    }
}
