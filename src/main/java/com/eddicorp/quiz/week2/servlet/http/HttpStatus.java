package com.eddicorp.quiz.week2.servlet.http;

/**
 * HTTP Status를 표현합니다.
 */
public enum HttpStatus {
    OK(200, "OK"),
    FOUND(302, "Found"),
    NOT_IMPLEMENTED(501, "Not Implemented");


    private final int httpStatusCode;
    private final String reasonPhrase;

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }

    HttpStatus(int httpStatusCode, String reasonPhrase) {
        this.httpStatusCode = httpStatusCode;
        this.reasonPhrase = reasonPhrase;
    }
}
