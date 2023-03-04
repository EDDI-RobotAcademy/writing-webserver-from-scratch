package com.eddicorp.application.controller;

import com.eddicorp.http.HttpStatus;
import com.eddicorp.http.HttpRequest;
import com.eddicorp.http.HttpResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

public class StaticFileController implements Controller {

    private static final String STATIC_FILE_PATH = "pages";

    @Override
    public void handle(HttpRequest request, HttpResponse response) {
        final String uri = request.getUri();
        final String filePath = determineFilePathFromUri(uri);
        final String pathToLoad = Paths.get(STATIC_FILE_PATH, filePath).toString();
        try (
                final InputStream staticFileInputStream = this.getClass().getClassLoader().getResourceAsStream(pathToLoad);
                final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ) {
            final byte[] rawContent = readRawContentFromStaticFile(staticFileInputStream, byteArrayOutputStream);
            final String mimeType = determineMimeType(uri);
            response.setHeader("Content-Type", mimeType);
            response.setHeader("Content-Length", String.valueOf(rawContent.length));
            response.setStatus(HttpStatus.OK);
            response.renderRawBody(rawContent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String determineFilePathFromUri(String uri) {
        if ("/".equals(uri)) {
            return "index.html";
        } else {
            return uri;
        }
    }

    private String determineMimeType(String uri) {
        final int lastIndexOf = uri.lastIndexOf(".");
        final String extension = uri.substring(lastIndexOf + 1).trim();
        if ("html".equals(extension)) {
            return "text/html;charset=utf-8";
        }

        if ("css".equals(extension)) {
            return "text/css;charset=utf-8";
        }

        if ("svg".equals(extension)) {
            return "image/svg+xml";
        }

        if ("ico".equals(extension)) {
            return "image/x-icon";
        }

        return null;
    }

    private static byte[] readRawContentFromStaticFile(InputStream staticFileInputStream, ByteArrayOutputStream byteArrayOutputStream) throws IOException {
        int readCount;
        final byte[] buffer = new byte[8192];
        while ((readCount = staticFileInputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, readCount);
        }
        return byteArrayOutputStream.toByteArray();
    }
}
