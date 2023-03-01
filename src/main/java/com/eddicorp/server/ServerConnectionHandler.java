package com.eddicorp.server;

import com.eddicorp.WebApplication;
import com.eddicorp.http.HttpRequest;
import com.eddicorp.http.HttpResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.Paths;
import java.util.Map;

public class ServerConnectionHandler {
    private final Socket connection;

    public ServerConnectionHandler(Socket connection) {
        this.connection = connection;
    }

    public void handleHttp() throws IOException {
        try (
                final InputStream inputStream = connection.getInputStream();
                final OutputStream outputStream = connection.getOutputStream();
        ) {
            final HttpRequest httpRequest = new HttpRequest(inputStream);
            final String uri = httpRequest.getUri();
            final String method = httpRequest.getMethod();
            final Map<String, String> headerMap = httpRequest.getHeaderMap();

            String filename;

            if("GET".equals(method)) {
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
                final HttpResponse httpResponse = new HttpResponse(outputStream);
                // 1. 상태라인
                // - HTTP/1.1 200 OK
                httpResponse.setHttpStatus(HttpResponse.HttpStatus.OK);
                // 2. 헤더
                // - Content-Type: text/html; charset=utf-8
                httpResponse.setHeader("Content-Type", mimeType);
                // - Content-Length: rawFileToServe.length
                httpResponse.setHeader("Content-Length", String.valueOf(rawFileToServe.length));
                // 3. 바디
                httpResponse.setBody(rawFileToServe);

                // 응답하기!
                httpResponse.flush();
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
