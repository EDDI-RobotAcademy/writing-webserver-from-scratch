package com.eddicorp.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
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
        final String requestLine = readLine(inputStream);
        final String[] partsOfRequestLine = requestLine.split(" ");
        this.httpMethod = HttpMethod.valueOf(partsOfRequestLine[0]);
        final String[] uriAndQueryString = partsOfRequestLine[1].split("\\?");
        this.uri = uriAndQueryString[0].trim();
        parseHeaders(inputStream);
        final byte[] rawBody = parseBody(inputStream);
        parseCookies();
        parseParameters(rawBody);
        this.rawBody = rawBody;
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

    private void parseParameters(byte[] rawBody) throws UnsupportedEncodingException {
        final String contentType = headerMap.get("Content-Type");
        if ("application/x-www-form-urlencoded".equals(contentType) && rawBody != null) {
            final String urlEncodedForm = new String(rawBody);
            final String decoded = URLDecoder.decode(urlEncodedForm, StandardCharsets.UTF_8.name());
            final String[] keyAndValues = decoded.split("&");
            for (String keyAndValue : keyAndValues) {
                final String[] split = keyAndValue.split("=");
                if (split.length > 1) {
                    parameterMap.put(split[0].trim(), split[1].trim());
                }
            }
        }
    }

    private void parseHeaders(InputStream inputStream) throws IOException {
        String rawHeader;
        while (!"".equals((rawHeader = readLine(inputStream)))) {
            final String[] headerAndValues = rawHeader.split(":");
            final String headerName = headerAndValues[0].trim();
            final String headerValue = headerAndValues[1].trim();
            headerMap.put(headerName, headerValue);
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
                    throw new IllegalStateException("Unable to parse line.");
                }
            }
            stringBuilder.append(currentChar);
        }
        throw new IllegalStateException("Unable to find CRLF");
    }

    private static byte[] parseBody(InputStream inputStream) throws IOException {
        if (inputStream.available() > 0) {
            final byte[] bodyBytes = new byte[inputStream.available()];
            inputStream.read(bodyBytes);
            return bodyBytes;
        } else {
            return null;
        }
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
        if (create) {
            final String newSessionId = SessionManager.createNewSession();
            return SessionManager.getSession(newSessionId);
        }
        final Cookie sessionCookie = cookieMap.get(SessionManager.SESSION_KEY_NAME);
        if (sessionCookie != null) {
            final String sessionId = sessionCookie.getValue();
            return SessionManager.getSession(sessionId);
        }
        return null;
    }
}
