package com.eddicorp.utils;

import com.eddicorp.WebApplication;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileHandler {

    public static final String STATIC_FILE_PATH = "pages";

    public static byte[] readFileFromResourceStream(String filePath) throws IOException {
        try (
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

    public static String getMemeTypeByFileExtension(String extension) {
        if ("html".equals(extension)) {
            return "text/html; charset=utf-8";
        }

        if ("css".equals(extension)) {
            return "text/css; charset=utf-8";
        }

        if ("svg".equals(extension)) {
            return "image/svg+xml";
        }

        if ("ico".equals(extension)) {
            return "image/x-icon";
        }

        return "text/html; charset=utf-8";
    }
}
