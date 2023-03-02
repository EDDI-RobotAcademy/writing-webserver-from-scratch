package com.eddicorp.application.controller;

import com.eddicorp.WebApplication;
import com.eddicorp.http.request.HttpRequest;
import com.eddicorp.http.response.HttpResponse;
import com.eddicorp.http.response.HttpStatus;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

public class StaticFileController implements Controller {

    private static final String STATIC_FILE_PATH = "pages";

    @Override
    public void handle(HttpRequest request, HttpResponse response) throws IOException {
        final String uri = request.getUri();
        String fileName = uri;
        String extension = "";

        if ("/".equals(uri)) {
            fileName = "index.html";
        }
        final String filePath = Paths.get(STATIC_FILE_PATH, fileName).toString();
        final byte[] rawFileToServe = readFileFromResourceStream(filePath);

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

        // 응답 만들기
        // 1. 상태라인
        // - HTTP/1.1 200 OK
        response.setHttpStatus(HttpStatus.OK);
        // 2. 헤더
        // - Content-Type: text/html; charset=utf-8
        response.setHeader("Content-Type", mimeType);
        // - Content-Length: rawFileToServe.length
        assert rawFileToServe != null;
        response.setHeader("Content-Length", String.valueOf(rawFileToServe.length));
        // 3. 바디
        response.setBody(rawFileToServe);

        // 응답하기!
        response.flush();
    }

    private static byte[] readFileFromResourceStream(String filePath) throws IOException {
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
