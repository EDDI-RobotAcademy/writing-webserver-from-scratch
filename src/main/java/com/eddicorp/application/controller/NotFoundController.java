package com.eddicorp.application.controller;

import com.eddicorp.http.HttpStatus;
import com.eddicorp.http.HttpRequest;
import com.eddicorp.http.HttpResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

public class NotFoundController implements Controller {
    private static final String STATIC_FILE_PATH = "pages";

    @Override
    public void handle(HttpRequest request, HttpResponse response) {
        final String pathToLoad = Paths.get(STATIC_FILE_PATH, "not-found.html").toString();
        try (
                final InputStream staticFileInputStream = this.getClass().getClassLoader().getResourceAsStream(pathToLoad);
                final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ) {
            int readCount;
            final byte[] buffer = new byte[8192];
            while ((readCount = staticFileInputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, readCount);
            }
            final byte[] bytes = byteArrayOutputStream.toByteArray();
            response.setHeader("Content-Type", "text/html;charset=utf-8");
            response.setHeader("Content-Length", String.valueOf(bytes.length));
            response.setStatus(HttpStatus.OK);
            response.renderRawBody(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
