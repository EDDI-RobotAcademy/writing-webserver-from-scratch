package com.eddicorp.http.response;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class HttpResponse {

    private static final String CRLF = "\r\n";

    private final OutputStream outputStream;
    private final Map<String, String> responseHeaders = new HashMap<>();
    private HttpStatus httpStatus = null;

    public HttpResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void setStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public void setHeader(String headerName, String headerValue) {
        responseHeaders.put(headerName, headerValue);
    }

    public void renderResponseWithBody(byte[] rawBody) {
    }
}
