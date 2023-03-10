package com.eddicorp.http.request;

import com.eddicorp.http.session.Cookie;
import com.eddicorp.http.session.HttpSession;
import com.eddicorp.http.session.SessionManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
    private final String uri;
    private final HttpMethod httpMethod;
    private final Map<String, String> headerMap = new HashMap<>();

    private final Map<String, Cookie> cookieMap = new HashMap<>();


    private final String body;

    public String getUri() {
        return uri;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }


    public String getBody() {
        return body;
    }

    public Map<String, String> getHeaderMap() {
        return headerMap;
    }

    public HttpRequest(InputStream inputStream) throws IOException {
        final String rawRequestLine = readLine(inputStream);
        final String[] partsOfRequestLine = rawRequestLine.split(" ");
        this.httpMethod = HttpMethod.valueOf(partsOfRequestLine[0]);
        this.uri = partsOfRequestLine[1];

        String header;
        while (!"".equals(header = readLine(inputStream))) {
            final String[] headerAndValue = header.split(":");
            final String headerName = headerAndValue[0].trim();
            final String headerValue = headerAndValue[1].trim();
            headerMap.put(headerName, headerValue);
        }


        final int available = inputStream.available();
        final byte[] body = new byte[available];
        inputStream.read(body, 0, available);
        parseCookies();
        this.body = new String(body);
    }

    private void parseCookies() {
        final String rawCookie = headerMap.get("Cookie");
        if (rawCookie == null) {
            return;
        }
        final String[] rawCookies = rawCookie.split(";");
        for (String raw : rawCookies) {
            final String[] keyAndValue = raw.split("=");
            final Cookie cookie = new Cookie(keyAndValue[0].trim(), keyAndValue[1].trim());
            cookieMap.put(cookie.getName(), cookie);
        }
    }

    private static String readLine(InputStream inputStream) throws IOException {
        final StringBuilder stringBuilder = new StringBuilder();
        int readCharacter;
        while ((readCharacter = inputStream.read()) != -1) {
            final char currentChar = (char) readCharacter;
            if (currentChar == '\r') {
                if (((char) inputStream.read()) == '\n') {
                    return stringBuilder.toString();
                } else {
                    throw new IllegalStateException("Invalid HTTP request.");
                }
            }
            stringBuilder.append(currentChar);
        }
        throw new IllegalStateException("Unable to find CRLF");
    }

    public HttpSession getSession() {
        return getSession(false);
    }

    public HttpSession getSession(Boolean create) {
        if (create) {
            final String newSessionId = SessionManager.createNewSession();
            return SessionManager.getSession(newSessionId);
        }
        final Cookie sessionCookie = cookieMap.get(SessionManager.SESSION_KEY_NAME);
        if (sessionCookie != null) {
            System.out.println(sessionCookie);
            final String sessionId = sessionCookie.getValue();
            return SessionManager.getSession(sessionId);
        }
        return null;
    }
}
