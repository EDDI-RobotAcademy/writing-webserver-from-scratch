package com.eddicorp.application.controller;

import com.eddicorp.http.request.HttpRequest;
import com.eddicorp.http.response.HttpResponse;
import com.eddicorp.http.response.HttpStatus;

import java.io.IOException;
import java.nio.file.Paths;

import static com.eddicorp.utils.FileHandler.*;

public class NotFoundController implements Controller {

    @Override
    public void handle(HttpRequest request, HttpResponse response) throws IOException {
        final String uri = request.getUri();
        String extension = "";
        final String filePath = Paths.get(STATIC_FILE_PATH, "not-found.html").toString();
        final byte[] rawFileToServe = readFileFromResourceStream(filePath);

        final int indexOfPeriod = uri.lastIndexOf(".");
        if (indexOfPeriod != -1) {
            extension = uri.substring(indexOfPeriod + 1);
        }

        response.setHttpStatus(HttpStatus.OK);
        response.setHeader("Content-Type", getMemeTypeByFileExtension(extension));
        assert rawFileToServe != null;
        response.setHeader("Content-Length", String.valueOf(rawFileToServe.length));
        response.setBody(rawFileToServe);

        response.flush();
    }
}
