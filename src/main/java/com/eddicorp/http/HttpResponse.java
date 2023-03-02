package com.eddicorp.http;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class HttpResponse {

    private final OutputStream outputStream;
    private HttpStatus httpStatus;
    private final Map<String, String> headerMap = new HashMap<>();
    private byte[] body;

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public void setHeader(String headerName, String headerValue) {
        this.headerMap.put(headerName, headerValue);
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    public void flush() throws IOException {
        buildStatusLine();
        buildHeaders();
        outputStream.write(CRLF.getBytes(StandardCharsets.UTF_8));
        outputStream.write(body);
        outputStream.flush();
    }

    private static final String CRLF = "\r\n";

    private void buildHeaders() throws IOException {
        for (Map.Entry<String, String> entry : this.headerMap.entrySet()) {
            final String headerName = entry.getKey();
            final String headerValue = entry.getValue();
            final String header = headerName + ": " + headerValue + CRLF;
            outputStream.write(header.getBytes(StandardCharsets.UTF_8));
        }
    }

    private void buildStatusLine() throws IOException {
        final String statusLine = "HTTP/1.1 " + this.httpStatus.statusCode + " " + this.httpStatus.reasonPhrase + CRLF;
        outputStream.write(statusLine.getBytes(StandardCharsets.UTF_8));
    }

    public enum HttpStatus {
        OK(200, "OK");

        private final int statusCode;
        private final String reasonPhrase;

        HttpStatus(int statusCode, String reasonPhrase) {
            this.statusCode = statusCode;
            this.reasonPhrase = reasonPhrase;
        }
    }

    public HttpResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
    }


}
//회원가입, 로그인, 게시글 등록
// 회원가입 : post 됐다 치고
// 로그인 : set-Cookie + index.html에 로그인 정보 (게시글 ddta list 담아야함)
// 게시글 등록 : post 이후 get을 또 해서 -> html에 데이터 넣어서 값 body에 넣고 response에 태우기
