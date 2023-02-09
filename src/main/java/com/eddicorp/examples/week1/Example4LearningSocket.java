package com.eddicorp.examples.week1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Example4LearningSocket {

    public static void main(String[] args) throws IOException {
        final ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("클라이언트를 기다릴거에요.");
        final Socket clientSocket = serverSocket.accept();
        System.out.println("이 문장은 언제 실행될까요?");
    }
}
