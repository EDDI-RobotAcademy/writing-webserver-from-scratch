package com.eddicorp;


import com.eddicorp.http.request.HttpMethod;
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

    @DisplayName("1 is return from getParameter(page) when requested with GET /?page=1")
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

    @DisplayName("GET is returned from getHttpMethod() when requested with GET /")
    @Test
    void test3() throws IOException {
        // given
        final String rawHttpRequestString = "GET / HTTP/1.1\r\n";
        final byte[] rawHttpRequest = rawHttpRequestString.getBytes(StandardCharsets.UTF_8);
        final InputStream requestInputStream = new ByteArrayInputStream(rawHttpRequest);

        final HttpRequest sut = new HttpRequest(requestInputStream);

        // when
        final String expected = "GET";
        final HttpMethod actual = sut.getHttpMethod();

        // then
        assertEquals(expected, actual.name());
    }

    @DisplayName("localhost:8080 is returned from getHeaderMap().get('Host') when requested with headers Host: localhost:8080")
    @Test
    void test4() throws IOException {
        // given
        final String rawHttpRequestString = """
                GET / HTTP/1.1\r
                Host: localhost:8080\r
                """;
        final byte[] rawHttpRequest = rawHttpRequestString.getBytes(StandardCharsets.UTF_8);
        final InputStream requestInputStream = new ByteArrayInputStream(rawHttpRequest);

        final HttpRequest sut = new HttpRequest(requestInputStream);

        // when
        final String expected = "localhost:8080";
        final String actual = sut.getHeaderMap().get("Host");

        // then
        assertEquals(expected, actual);
    }
}