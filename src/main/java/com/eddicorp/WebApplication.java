package com.eddicorp;

import com.eddicorp.http.HttpRequest;
import com.eddicorp.http.HttpResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Paths;

public class WebApplication {

    public static void main(String[] args) throws Exception  {
        final ServerSocket serverSocket = new ServerSocket(8080);
        Socket connection;

        //인풋 데이터 확인
        while((connection = serverSocket.accept()) != null) {
            try(
                    //input, output Stream은 final 상수로 해야한다. 다른곳에서 잘못된 값 변경을 방지
                    final InputStream inputStream = connection.getInputStream();
                    final OutputStream outputStream = connection.getOutputStream();
                    ) {

                //httpRequest에 inputStream정보 전달하여 uri정보 가지고 오기
                final HttpRequest httpRequest = new HttpRequest(inputStream);
                final String uri =  httpRequest.getUri();

                //file name 지정해주기 디폴트값은 index.html
                String filename;
                if("/".equals(uri)){
                    filename = "index.html";
                }else {
                    filename = uri;
                }

                //뒤에 확장자 확인
                String extension = null;
                final int indexOfPeriod = uri.lastIndexOf(".");
                if(indexOfPeriod != -1) {
                    extension = uri.substring(indexOfPeriod +1);
                }
                System.out.println("extension = " +extension);

                // fallback으로 기본 값 지정
                String mimeType = "text/html; charset=utf-8";
                if ("html".equals(extension)) {
                    mimeType = "text/html; charset=utf-8";
                }

                if ("css".equals(extension)) {
                    mimeType = "text/css; charset=utf-8";
                }

                if ("svg".equals(extension)) {
                    mimeType = "image/svg+xml";
                }

                if ("ico".equals(extension)) {
                    mimeType = "image/x-icon";
                }

                // rawFileToServe : body에 넣을 데이터
                final byte[] rawFileToServe = readFileFromResourceStream(filename);

                // 응답 만들기
                final HttpResponse httpResponse = new HttpResponse(outputStream);
                // 1. 상태라인
                // - HTTP/1.1 200 OK
                httpResponse.setHttpStatus(HttpResponse.HttpStatus.OK);
                // 2. 헤더
                // - Content-Type: text/html; charset=utf-8
                httpResponse.setHeader("Content-Type", mimeType);
                // - Content-Length: rawFileToServe.length
                httpResponse.setHeader("Content-Length", String.valueOf(rawFileToServe.length));
                // 3. 바디
                httpResponse.setBody(rawFileToServe);

                // 응답하기!
                httpResponse.flush();
            }
        }
    }

    private static byte[] readFileFromResourceStream(String filename) throws IOException {
        //string filePath 얻어서 byte로 변환
        final String filePath = Paths.get("pages", filename).toString();

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
           return baos.toByteArray();
        }
    }
}
//1.페이지 구동먼저
//2.블로그앱 힌트버전 참고
