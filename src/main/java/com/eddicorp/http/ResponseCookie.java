package com.eddicorp.http;

public class ResponseCookie {

    private final String name;
    private final String value;
    private final String path;
    private final String domain;
    private final int maxAge;

    public ResponseCookie(String name, String value, String path, String domain, int maxAge) {
        this.name = name;
        this.value = value;
        this.path = path;
        this.domain = domain;
        this.maxAge = maxAge;
    }

    public String build() {
        return String.format("%s=%s; Path=%s; Domain=%s; Max-Age=%d", name, value, path, domain, maxAge);
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public String getPath() {
        return path;
    }

    public String getDomain() {
        return domain;
    }

    public int getMaxAge() {
        return maxAge;
    }
}
