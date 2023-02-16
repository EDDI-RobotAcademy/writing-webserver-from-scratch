package com.eddicorp.quiz.week1.http.request;

import com.eddicorp.quiz.week1.http.session.HttpSession;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {

    private final String uri;
    private final HttpMethod httpMethod;

    private final Map<String, String> headerMap = new HashMap<>();

    private final Map<String, String> parameterMap = new HashMap<>();

    private final Map<String, Cookie> cookieMap = new HashMap<>();

    private final byte[] rawBody;

    public HttpRequest(final InputStream inputStream) throws IOException {
        this.uri = "";
        this.httpMethod = HttpMethod.GET;
        this.rawBody = new byte[0];
    }

    public String getUri() {
        return uri;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public String getParameter(String parameterName) {
        return parameterMap.get(parameterName);
    }

    public HttpSession getSession() {
        return getSession(false);
    }

    public HttpSession getSession(boolean create) {
        return null;
    }
}
