package com.eddicorp.http;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class HttpResponse {

    private static final String CRLF = "\r\n";

    private final OutputStream outputStream;
    private final Map<String, String> headerMap = new HashMap<>();
    private HttpStatus httpStatus = null;

    public HttpResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void setStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public void setHeader(String headerName, String headerValue) {
        headerMap.put(headerName, headerValue);
    }

    public void renderRawBody(byte[] rawBody) {
        try {
            final byte[] rawResponseLineAndHeaders = buildResponseLineAndHeadersInRawBytes();
            outputStream.write(rawResponseLineAndHeaders);
            if (rawBody != null) {
                outputStream.write(rawBody);
            }
            outputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] buildResponseLineAndHeadersInRawBytes() {
        Objects.requireNonNull(httpStatus);
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("HTTP/1.1").append(" ")
                .append(httpStatus.getHttpStatusCode()).append(" ")
                .append(httpStatus.getReasonPhrase()).append(CRLF);

        for (Map.Entry<String, String> headerMapEntry : headerMap.entrySet()) {
            stringBuilder
                    .append(headerMapEntry.getKey()).append(": ")
                    .append(headerMapEntry.getValue())
                    .append(CRLF);
        }

        stringBuilder.append(CRLF);
        final String responseLineAndHeaders = stringBuilder.toString();
        final byte[] rawResponseLineAndHeaders = responseLineAndHeaders.getBytes(StandardCharsets.UTF_8);
        return rawResponseLineAndHeaders;
    }
}
