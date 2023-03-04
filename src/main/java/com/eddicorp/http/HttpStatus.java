package com.eddicorp.http;

public enum HttpStatus {
    OK(200, "OK"),
    FOUND(302, "Found");


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
