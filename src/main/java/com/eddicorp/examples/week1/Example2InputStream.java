package com.eddicorp.examples.week1;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Example2InputStream {

    public static void main(String[] args) {
        final String message = "Hello!";
        final byte[] requestBytes = message.getBytes(StandardCharsets.UTF_8);

        try (final InputStream byteArrayInputStream = new ByteArrayInputStream(requestBytes)) {
            System.out.println("requestBytes.length = " + requestBytes.length);
            System.out.println("byteArrayInputStream.available() = " + byteArrayInputStream.available());
            System.out.println((char) byteArrayInputStream.read());
            System.out.println((char) byteArrayInputStream.read());
            System.out.println((char) byteArrayInputStream.read());
            System.out.println((char) byteArrayInputStream.read());
            System.out.println((char) byteArrayInputStream.read());
            System.out.println((char) byteArrayInputStream.read());
            System.out.println("byteArrayInputStream.available() = " + byteArrayInputStream.available());
            System.out.println(byteArrayInputStream.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (final InputStream byteArrayInputStream = new ByteArrayInputStream(requestBytes)) {
            final byte[] buffer = new byte[6];
            final int read = byteArrayInputStream.read(buffer);
            System.out.println("read = " + read);
            final String messageFromBuffer = new String(buffer);
            System.out.println("messageFromBuffer = " + messageFromBuffer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
