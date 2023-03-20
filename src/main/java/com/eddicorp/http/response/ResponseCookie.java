package com.eddicorp.http.response;

public record ResponseCookie(String name, String value, String path, String domain, int maxAge) {

    public String build() {
        return String.format("%s=%s; Path=%s; Domain=%s; Max-Age=%d", name, value, path, domain, maxAge);
    }
}
