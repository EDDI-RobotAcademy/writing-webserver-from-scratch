package com.eddicorp.quiz.week3;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest3 {

    private final String method;
    private final String uri;
    private final Map<String, String> params = new HashMap<>();

    public HttpRequest3(InputStream inputStream) throws IOException {
        final String requestLine = readLine(inputStream);
        final String[] partsOfRequestLine = requestLine.split(" ");
        this.method = partsOfRequestLine[0];
        final String uriWithQueryString = partsOfRequestLine[1];
        final String[] uriAndQueryString = uriWithQueryString.split("\\?");
        this.uri = uriAndQueryString[0];
        if(uriAndQueryString.length > 1) {
            final String[] queryStringKeyValues = uriAndQueryString[1].split("&");
            for(String queryStringValue : queryStringKeyValues) {
                final String[] keyAndValue = queryStringValue.split("=");
                params.put(keyAndValue[0], keyAndValue[1]);
            }
        }
    }

    private String readLine (InputStream inputStream) throws IOException {
        final StringBuilder stringBuilder = new StringBuilder();
        int rawReadchar;
        while((rawReadchar = inputStream.read()) != -1) {
            final char readChar = (char)rawReadchar;
            if(readChar == '\r') {
                final char nextChar = (char)inputStream.read();
                if(nextChar == '\n'){
                    return stringBuilder.toString();
                }
            }
            stringBuilder.append((char)rawReadchar);
        }
        return null;
    }


    public String getUri() {
        return uri;
    }

    public String getMethod() {
        return method;
    }

    public String getParams(String name) {
        return params.get(name);
    }
}
