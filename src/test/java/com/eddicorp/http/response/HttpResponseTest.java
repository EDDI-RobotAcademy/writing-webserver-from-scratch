package com.eddicorp.http.response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HttpResponseTest {

    @DisplayName("setHttpStatus(HttpStatus.OK)를 실행 시 HTTP/1.1 200 OK\n\n를 response로 반환한다")
    @Test
    void test1() throws IOException {
        // given
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream(4096);
        final HttpResponse sut = new HttpResponse(outputStream);
        sut.setHttpStatus(HttpStatus.OK);

        sut.flush();

        // when
        String expected = "HTTP/1.1 200 OK\r\n\r\n";
        String actual = outputStream.toString(StandardCharsets.UTF_8);

        // then
        assertEquals(expected, actual);
    }

    @DisplayName("setHeader(\"Content-Type\", \"application/json\")를 실행 시 Content-Type\", \"application/json를 header로 반환한다")
    @Test
    void test2() throws IOException {
        // given
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream(4096);
        final HttpResponse sut = new HttpResponse(outputStream);
        sut.setHttpStatus(HttpStatus.OK);
        sut.setHeader("Content-Type", "application/json");

        sut.flush();

        // when
        String actual = outputStream.toString(StandardCharsets.UTF_8);
        String expected = "HTTP/1.1 200 OK\r\nContent-Type: application/json\r\n\r\n";

        // then
        assertEquals(expected, actual);
    }

    @DisplayName("setBody(\"Test body\")를 실행 시 Test body를 body로 반환한다")
    @Test
    void test3() throws IOException {
        // given
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream(4096);
        final HttpResponse sut = new HttpResponse(outputStream);
        sut.setHttpStatus(HttpStatus.OK);
        sut.setBody("Test body".getBytes(StandardCharsets.UTF_8));

        sut.flush();

        // when
        String expected = "HTTP/1.1 200 OK\r\n\r\nTest body";
        String actual = outputStream.toString(StandardCharsets.UTF_8);

        // then
        assertEquals(expected, actual);
    }
}