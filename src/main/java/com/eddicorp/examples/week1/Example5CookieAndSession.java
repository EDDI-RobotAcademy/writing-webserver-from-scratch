package com.eddicorp.examples.week1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Example5CookieAndSession {

    public static void main(String[] args) throws IOException, InterruptedException {
        final ServerSocket serverSocket = new ServerSocket(8080);
        Socket clientSocket;
        while ((clientSocket = serverSocket.accept()) != null) {
            try (
                    final InputStream inputStream = clientSocket.getInputStream();
                    final OutputStream outputStream = clientSocket.getOutputStream();
            ) {
                // 작성
            }
        }
    }
}
