package com.eddicorp.http;

import com.eddicorp.WebApplication;
import com.eddicorp.application.controller.PostController;
import com.eddicorp.application.controller.UserController;
import com.eddicorp.http.session.HttpSession;
import com.eddicorp.http.session.SessionManager;

import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
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
        parseHeader(inputStream);
        final byte[] rawBody = parseBody(inputStream);
        parseCookies();
        parseParameters(rawBody);
        this.rawBody = rawBody;
    }

    private void parseParameters(byte[] rawBody) throws UnsupportedEncodingException {
        final String contentType = headerMap.get("Content-Type");
        if("application/x-www-form-urlencoded".equals(contentType) && rawBody != null) {
            final String urlEncodedForm = new String(rawBody);
            final String decoded = URLDecoder.decode(urlEncodedForm, StandardCharsets.UTF_8.name());
            final String[] keyAndValues = decoded.split("&");
            for(String keyAndValue : keyAndValues) {
                final String[] split = keyAndValue.split("=");
                if(split.length > 1){
                    parameterMap.put(split[0].trim(), split[1].trim());
                }
            }
        }
    }

    private void parseCookies() {
        final String rawCookie = headerMap.get("Cookie");
        if(rawCookie == null){
            return;
        }
    }

    private void parseHeader(InputStream inputStream) throws IOException {
        String rawHeader;
        while (!"".equals((rawHeader = readLine(inputStream)))){
            final String[] headerAndValues = rawHeader.split(":");
            final String headerName = headerAndValues[0].trim();
            final String headerValue = headerAndValues[1].trim();
            headerMap.put(headerName, headerValue);
        }
    }

    public static String readLine(InputStream inputStream) throws IOException {
        final StringBuilder stringBuilder = new StringBuilder();
        int readCharacter;
        while ((readCharacter = inputStream.read()) != -1) {
            final char currentChar = (char) readCharacter;
            if(currentChar == '\r'){
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

    private static byte[] parseBody (InputStream inputStream) throws IOException {
        if(inputStream.available() > 0) {
            final byte[] bodyBytes = new byte[inputStream.available()];
            inputStream.read(bodyBytes);
            return bodyBytes;
        }else {
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
        if(create) {
            final String newSessionId = SessionManager.createNewSession();
            return SessionManager.getSession(newSessionId);
        }
        final Cookie sessionCookie = cookieMap.get(SessionManager.SESSION_KEY_NAME);
        if(sessionCookie != null) {
            final String sessionId = sessionCookie.getValue();
            return SessionManager.getSession(sessionId);
        }
        return null;
    }


//    private  String uri;
//    private  String method;
//    private String body;
//    private String result;
//
//
//
//    public String getUri() {
//        return uri;
//    }
//    public String getResult() {
//        return result;
//    }
//
//    public HttpRequest(InputStream inputStream) throws IOException {
//        final String rawRequestLine = readLine(inputStream);
//        final String[] parsOfRequestLine = rawRequestLine.split(" ");
//        this.method = parsOfRequestLine[0];
//        this.uri = parsOfRequestLine[1];
//        if(method.equals("POST")) this.body = findBody(inputStream);
//
//    }
//
//
//
//    private static String readLine(InputStream inputStream) throws IOException {
//        final StringBuilder stringBuilder = new StringBuilder();
//        int readCharacter;
//        while ((readCharacter = inputStream.read()) != -1) {
//            final char currentChar = (char) readCharacter;
//            if (currentChar == '\r') {
//                final char nextChar = (char) inputStream.read();
//                if (nextChar == '\n') {
//                    return stringBuilder.toString();
//                } else {
//                    throw new IllegalStateException("Invalid HTTP request.");
//                }
//            }
//            stringBuilder.append(currentChar);
//        }
//        throw new IllegalStateException("Unable to find CRLF");
//    }
//
//    private String findBody(InputStream inputStream) throws IOException {
//        String header;
//        while(!"".equals(header = readLine(inputStream))) {
////            System.out.println("header = " +header);
//        }
//
//        final int available = inputStream.available();
//        final byte[] body = new byte[available];
//        inputStream.read(body,0,available);
//        final String form = new String(body);
//        System.out.println("body = " +form);
//
//        return form;
//    }
//
//    public byte[] sendToController(String uri, String filename) throws IOException {
//        PostController postController;
//        UserController userController = new UserController();
//        switch (uri) {
//            case "/" :
//                byte[] readIndexFile = readFileFromResourceStream(filename);
//                return readIndexFile;
//            case "/users" :
//               String result = userController.createUser(this.body);
//               this.uri = "/";
//               byte[] readFileData = readFileFromResourceStream("index.html");
//               String userData =  userController.getUserData();
//                System.out.println(userData);
//               return readFileData;
//            case "/logout" :
//
//                break;
//            case "/post" :
//
//                break;
//
//            case "/login.html" :
//                String loginResult = userController.login();
//                if(loginResult.equals("OK")){
//                    userController.setCookie();
//                    byte[] readIndexLogin = readFileFromResourceStream(filename);
//                    return readIndexLogin;
//                } else return null;
//
//            case "/signup.html" :
//                byte[] readIndexSignup = readFileFromResourceStream(uri);
//                return readIndexSignup;
//        }
//        return null;
//    }
//
//    private static byte[] readFileFromResourceStream(String filename) throws IOException {
//        //string filePath 얻어서 byte로 변환
//        final String filePath = Paths.get("pages", filename).toString();
//        System.out.println(filePath);
//        try (
//                //this.class.getClassLoader().getResourceAsStream(filePath);
//                //의미 : resource의 파일 디렉토리 정보를 가지고 온다.
//                final InputStream resourceInputStream = WebApplication.class
//                        .getClassLoader()
//                        .getResourceAsStream(filePath);
//                final ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        ) {
//            if (resourceInputStream == null) {
//                return null;
//            }
//
//            int readCount = 0;
//            final byte[] readBuffer = new byte[8192];
//            while ((readCount = resourceInputStream.read(readBuffer)) != -1) {
//                baos.write(readBuffer, 0, readCount);
//            }
//            String read = readBuffer.toString();
//            System.out.println(read);
//            return baos.toByteArray();
//        }
//    }
}