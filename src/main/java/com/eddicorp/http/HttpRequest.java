package com.eddicorp.http;

import com.eddicorp.WebApplication;
import com.eddicorp.application.controller.PostController;
import com.eddicorp.application.controller.UserController;

import java.io.*;
import java.nio.file.Paths;

public class HttpRequest {
    private  String uri;
    private  String method;
    private String body;
    private String result;



    public String getUri() {
        return uri;
    }
    public String getResult() {
        return result;
    }

    public HttpRequest(InputStream inputStream) throws IOException {
        final String rawRequestLine = readLine(inputStream);
        final String[] parsOfRequestLine = rawRequestLine.split(" ");
        this.method = parsOfRequestLine[0];
        this.uri = parsOfRequestLine[1];
        if(method.equals("POST")) this.body = findBody(inputStream);

    }



    private static String readLine(InputStream inputStream) throws IOException {
        final StringBuilder stringBuilder = new StringBuilder();
        int readCharacter;
        while ((readCharacter = inputStream.read()) != -1) {
            final char currentChar = (char) readCharacter;
            if (currentChar == '\r') {
                final char nextChar = (char) inputStream.read();
                if (nextChar == '\n') {
                    return stringBuilder.toString();
                } else {
                    throw new IllegalStateException("Invalid HTTP request.");
                }
            }
            stringBuilder.append(currentChar);
        }
        throw new IllegalStateException("Unable to find CRLF");
    }

    private String findBody(InputStream inputStream) throws IOException {
        String header;
        while(!"".equals(header = readLine(inputStream))) {
//            System.out.println("header = " +header);
        }

        final int available = inputStream.available();
        final byte[] body = new byte[available];
        inputStream.read(body,0,available);
        final String form = new String(body);
        System.out.println("body = " +form);

        return form;
    }

    public byte[] sendToController(String uri, String filename) throws IOException {
        PostController postController;
        UserController userController = new UserController();
        switch (uri) {
            case "/" :
                byte[] readIndexFile = readFileFromResourceStream(filename);
                return readIndexFile;
            case "/users" :
               String result = userController.createUser(this.body);
               this.uri = "/";
               byte[] readFileData = readFileFromResourceStream("index.html");
               String userData =  userController.getUserData();
                System.out.println(userData);
               return readFileData;
            case "/logout" :

                break;
            case "/post" :

                break;
            case "/login.html" :
                byte[] readIndexLogin = readFileFromResourceStream(filename);
                return readIndexLogin;
            case "/signup.html" :
                byte[] readIndexSignup = readFileFromResourceStream(uri);
                return readIndexSignup;
        }
        return null;
    }

    private static byte[] readFileFromResourceStream(String filename) throws IOException {
        //string filePath 얻어서 byte로 변환
        final String filePath = Paths.get("pages", filename).toString();
        System.out.println(filePath);
        try (
                //this.class.getClassLoader().getResourceAsStream(filePath);
                //의미 : resource의 파일 디렉토리 정보를 가지고 온다.
                final InputStream resourceInputStream = WebApplication.class
                        .getClassLoader()
                        .getResourceAsStream(filePath);
                final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ) {
            if (resourceInputStream == null) {
                return null;
            }

            int readCount = 0;
            final byte[] readBuffer = new byte[8192];
            while ((readCount = resourceInputStream.read(readBuffer)) != -1) {
                baos.write(readBuffer, 0, readCount);
            }
            String read = readBuffer.toString();
            System.out.println(read);
            return baos.toByteArray();
        }
    }
}