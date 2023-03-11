package com.eddicorp;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


public class HttpRequest {

    private final String method;
    private final String uri;
    private final Map<String, String> parameters = new HashMap<>();

    public HttpRequest(InputStream inputStream) throws IOException {
        final String requestLine = readLine(inputStream);
        final String[] partsOfRequestLine = requestLine.split(" ");
        this.method = partsOfRequestLine[0];
        final String uriWithQueryString = partsOfRequestLine[1];
        final String[] uriAndQueryString = uriWithQueryString.split("\\?");
        this.uri = uriAndQueryString[0];
        if (uriAndQueryString.length > 1) {
            final String[] queryStringKeyValues = uriAndQueryString[1].split("&");
            for (String queryStringKeyValue : queryStringKeyValues) {
                final String[] keyAndValue = queryStringKeyValue.split("=");
                parameters.put(keyAndValue[0], keyAndValue[1]);
            }
        }
    }

    private String readLine(InputStream inputStream) throws IOException {
        final StringBuilder stringBuilder = new StringBuilder();
        int rawReadChar;
        while ((rawReadChar = inputStream.read()) != -1) {
            final char readChar = (char) rawReadChar;
            if (readChar == '\r') {
                final char nextChar = (char) inputStream.read();
                if (nextChar == '\n') {
                    return stringBuilder.toString();
                }
            }
            stringBuilder.append((char) rawReadChar);
        }
        return null;
    }


    public String getUri() {
        return uri;
    }

    public String getParameter(String name) {
        return parameters.get(name);
    }

    public String getMethod() {
        return method;
    }
}
