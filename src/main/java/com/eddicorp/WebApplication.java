package com.eddicorp;

import com.eddicorp.http.HttpRequest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

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
                // 요청을 분석해서 적절한 응답을 주자
                final HttpRequest httpRequest = new HttpRequest(inputStream);
                final String uri = httpRequest.getUri();

                String filename;
                if ("/".equals(uri)) {
                    filename = "index.html";
                } else {
                    filename = uri;
                }

                String extension = null;
                final int indexOfPeriod = uri.lastIndexOf(".");
                if (indexOfPeriod != -1) {
                    extension = uri.substring(indexOfPeriod + 1);
                }
                System.out.println("extension = " + extension);

                // fallback으로 기본 값 지정
                String mimeType = "text/html; charset=utf-8";
                if ("html".equals(extension)) {
                    mimeType = "text/html; charset=utf-8";
                }

                if ("css".equals(extension)) {
                    mimeType = "text/css; charset=utf-8";
                }

                if ("svg".equals(extension)) {
                    mimeType = "image/svg+xml";
                }

                if ("ico".equals(extension)) {
                    mimeType = "image/x-icon";
                }

                // 이대로라면 문제가 생김. mime type을 지정해주지 않았기 때문에!
                final byte[] rawFileToServe = readFileFromResourceStream(filename);

                // 응답 만들기
                // 1. 상태라인
                // - HTTP/1.1 200 OK
                final String CRLF = "\r\n";
                final String statusLine = "HTTP/1.1 200 OK" + CRLF;
                outputStream.write(statusLine.getBytes(StandardCharsets.UTF_8));
                // 2. 헤더
                // - Content-Type: text/html; charset=utf-8
                final String contentTypeHeader = "Content-Type: " + mimeType + CRLF;
                outputStream.write(contentTypeHeader.getBytes(StandardCharsets.UTF_8));
                // - Content-Length: rawFileToServe.length
                final String contentLengthHeader = "Content-Length: " + rawFileToServe.length + CRLF;
                outputStream.write(contentLengthHeader.getBytes(StandardCharsets.UTF_8));
                outputStream.write(CRLF.getBytes(StandardCharsets.UTF_8));
                // 3. 바디
                outputStream.write(rawFileToServe);

                // 이렇게 하면 제대로 화면에는 안나옴.
                // 그 이유는 index.html 이외에 다른 친구들을 줘야하는데 대응이 안되어 있기 때문
            }
        }
    }


    private static byte[] readFileFromResourceStream(String fileName) throws IOException {
        final String filePath = Paths.get("pages", fileName).toString();
        try (
                final InputStream resourceInputStream = WebApplication.class
                        .getClassLoader()
                        .getResourceAsStream(filePath);
                final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ) {
            if (resourceInputStream == null) {
                return null;
            }

            int readCount = 0;
            final byte[] readBuffer = new byte[8192];
            while ((readCount = resourceInputStream.read(readBuffer)) != -1) {
                baos.write(readBuffer, 0, readCount);
            }
            return baos.toByteArray();
        }
    }
}
