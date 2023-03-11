package com.eddicorp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("요청 객체에 대한 테스트")
public class HttpRequestTest {

    @DisplayName("GET method로 uri /?page=1에 요청한 경우에 uri는 /")
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

    @DisplayName("GET method와 uri /?page=1로 요청한 경우 getParameter(page)를 호출하는 경우 1이 반환")
    @Test
    void test2() throws IOException {

        // given
        final String rawHttpRequestString = "GET /?page=1 HTTP/1.1\r\n";
        final byte[] rawHttpRequest = rawHttpRequestString.getBytes(StandardCharsets.UTF_8);
        final InputStream requestInputStream = new ByteArrayInputStream(rawHttpRequest);

        final HttpRequest sut = new HttpRequest(requestInputStream);

        // when
        final String expected = "1";
        final String actual = sut.getParameter("page");

        // then
        assertEquals(expected, actual);
    }

    @DisplayName("GET method와 uri /로 요청한 경우, getMethod가 호출되면 GET을 반환한다.")
    @Test
    void test() throws IOException {

        // given
        final String rawHttpRequestString = "GET / HTTP/1.1\r\n";
        final byte[] rawHttpRequest = rawHttpRequestString.getBytes(StandardCharsets.UTF_8);
        final InputStream requestInputStream = new ByteArrayInputStream(rawHttpRequest);

        final HttpRequest sut = new HttpRequest(requestInputStream);

        // when
        final String expected = "GET";
        final String actual = sut.getMethod();

        // then
        assertEquals(expected, actual);
    }
}
