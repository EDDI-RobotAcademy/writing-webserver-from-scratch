package com.eddicorp;

import java.io.InputStream;

public class WebApplication {

    public static void main(String[] args) throws Exception {
        // resources 디렉터리의 파일 읽어보기
        try (
                final InputStream inputStream = WebApplication.class
                        .getClassLoader()
                        .getResourceAsStream("pages/index.html")
        ) {
            int readCount = 0;
            final byte[] readBuffer = new byte[8192];
            while ((readCount = inputStream.read(readBuffer)) != -1) {
                final String result = new String(readBuffer);
                System.out.println(result);
            }
        }
    }
}
