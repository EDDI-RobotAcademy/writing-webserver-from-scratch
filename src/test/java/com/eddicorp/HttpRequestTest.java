package com.eddicorp;


import com.eddicorp.http.request.HttpRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HttpRequestTest {

    @DisplayName("uri is equal to / when requested with GET /?page=1")
    @Test
    void test1() throws IOException {
        // given
        final String rawHttpRequestString = "GET /?page=1 HTTP/1.1\r\n";
        final byte[] rawHttpRequest = rawHttpRequestString.getBytes(StandardCharsets.UTF_8);
        final InputStream requestInputStream = new ByteArrayInputStream(rawHttpRequest);

        final HttpRequest sut = new HttpRequest(requestInputStream);

        // when
        final String actual = sut.getUri();

        // then
        final String expected = "/";
        assertEquals(expected, actual);
    }

    @DisplayName("getParameter(page) returns 1 when requested with GET /?page=1")
    @Test
    void test2() throws IOException {
        // given
        final String rawHttpRequestString = "GET /?page=1 HTTP/1.1\r\n";
        final byte[] rawHttpRequest = rawHttpRequestString.getBytes(StandardCharsets.UTF_8);
        final InputStream requestInputStream = new ByteArrayInputStream(rawHttpRequest);

        final HttpRequest sut = new HttpRequest(requestInputStream);

        // when
        final String expected = "1";
        final String actual = sut.getParameters("page");

        // then
        assertEquals(expected, actual);
    }
}