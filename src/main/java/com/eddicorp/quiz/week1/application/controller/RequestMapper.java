package com.eddicorp.quiz.week1.application.controller;

import com.eddicorp.quiz.week1.http.request.HttpMethod;

import java.util.Objects;

public class RequestMapper {

    private final String uri;
    private final HttpMethod method;

    public RequestMapper(String uri, HttpMethod method) {
        this.uri = uri;
        this.method = method;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RequestMapper)) return false;
        RequestMapper that = (RequestMapper) o;
        return Objects.equals(uri, that.uri) && method == that.method;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uri, method);
    }
}
