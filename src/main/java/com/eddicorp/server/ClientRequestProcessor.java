package com.eddicorp.server;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientRequestProcessor implements Runnable {

    private final Socket clientSocket;
    private final RequestHandler requestHandler = new RequestHandler();

    public ClientRequestProcessor(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (
                final InputStream inputStream = clientSocket.getInputStream();
                final OutputStream outputStream = clientSocket.getOutputStream();
        ) {
            requestHandler.handle(inputStream, outputStream);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.err.println(throwable);
        }
    }
}
