package com.eddicorp;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class WebApplication {

    public static void main(String[] args) throws Exception {
        // index.html 브라우저에 띄워보기
        final ServerSocket serverSocket = new ServerSocket(8080);
        Socket connection;
        while ((connection = serverSocket.accept()) != null) {
            try (
                    final InputStream inputStream = connection.getInputStream();
                    final OutputStream outputStream = connection.getOutputStream()
            ) {
                try (
                        final InputStream resourceInputStream = WebApplication.class
                                .getClassLoader()
                                .getResourceAsStream("pages/index.html");
                        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ) {
                    int readCount = 0;
                    final byte[] readBuffer = new byte[8192];
                    while ((readCount = resourceInputStream.read(readBuffer)) != -1) {
                        baos.write(readBuffer, 0, readCount);
                    }
                    baos.toByteArray();
                }
            }
        }
    }
}
