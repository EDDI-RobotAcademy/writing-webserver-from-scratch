package com.eddicorp;

import com.eddicorp.server.ServerConnectionHandler;

import java.net.ServerSocket;
import java.net.Socket;

public class WebApplication {

    public static void main(String[] args) throws Exception {
        // index.html 브라우저에 띄워보기
        final ServerSocket serverSocket = new ServerSocket(8080);
        Socket connection;

        while ((connection = serverSocket.accept()) != null) {
            ServerConnectionHandler httpConnectionHandler = new ServerConnectionHandler(connection);
            httpConnectionHandler.handleHttp();
        }
    }
}
