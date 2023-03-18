package com.eddicorp;


import com.eddicorp.http.request.HttpMethod;
import com.eddicorp.http.request.HttpRequest;
import com.eddicorp.http.session.Cookie;
import com.eddicorp.http.session.HttpSession;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

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
        final String expected = "/";
        final String actual = sut.getUri();

        // then
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
        final String rawHttpRequestString =
                """
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

    @DisplayName("123 is returned from getCookieMap().get('id') when requested with headers Cookie: id=123")
    @Test
    void test5() throws IOException {
        // given
        final String rawHttpRequestString = """
                GET / HTTP/1.1\r
                Host: localhost:8080\r
                Cookie: id=123\r
                """;
        final byte[] rawHttpRequest = rawHttpRequestString.getBytes(StandardCharsets.UTF_8);
        final InputStream requestInputStream = new ByteArrayInputStream(rawHttpRequest);

        final HttpRequest sut = new HttpRequest(requestInputStream);

        // when
        final Cookie expected = new Cookie("id", "123");
        final Cookie actual = sut.getCookieMap().get("id");

        // then
        assertEquals(expected.getValue(), actual.getValue());
    }

    @DisplayName("userId=123 is returned from getBody() when requested with body userId=123")
    @Test
    void test6() throws IOException {
        // given
        final String rawHttpRequestString = """
                GET / HTTP/1.1\r
                Host: localhost:8080\r
                Content-Length: 10\r
                \r
                userId=123""";
        final byte[] rawHttpRequest = rawHttpRequestString.getBytes(StandardCharsets.UTF_8);
        final InputStream requestInputStream = new ByteArrayInputStream(rawHttpRequest);

        final HttpRequest sut = new HttpRequest(requestInputStream);

        // when
        final String expected = "userId=123";
        final String actual = sut.getBody();

        // then
        assertEquals(expected, actual);
    }

    @DisplayName("null is returned from getSession() when JSESSIONID cookie is not present")
    @Test
    void test7() throws IOException {
        // given
        final String rawHttpRequestString = """
                GET / HTTP/1.1\r
                Host: localhost:8080\r
                Content-Length: 10\r
                """;
        final byte[] rawHttpRequest = rawHttpRequestString.getBytes(StandardCharsets.UTF_8);
        final InputStream requestInputStream = new ByteArrayInputStream(rawHttpRequest);

        final HttpRequest sut = new HttpRequest(requestInputStream);

        // when
        final HttpSession httpSession = sut.getSession();

        // then
        assertNull(httpSession);
    }

    @DisplayName("HttpSession is created and returned from getSession(true) when JSESSIONID cookie is not present")
    @Test
    void test8() throws IOException {
        // given
        final String rawHttpRequestString = """
                GET / HTTP/1.1\r
                Host: localhost:8080\r
                Content-Length: 10\r
                """;
        final byte[] rawHttpRequest = rawHttpRequestString.getBytes(StandardCharsets.UTF_8);
        final InputStream requestInputStream = new ByteArrayInputStream(rawHttpRequest);

        final HttpRequest sut = new HttpRequest(requestInputStream);

        // when
        final HttpSession httpSession = sut.getSession(true);

        // then
        assertNotNull(httpSession);
    }

    @DisplayName("HttpSession is returned from getSession(false) when JSESSIONID cookie is present")
    @Test
    void test9() throws IOException {
        // given
        final String rawHttpRequestString = """
                GET / HTTP/1.1\r
                Host: localhost:8080\r
                Content-Length: 10\r
                Cookie: JSESSIONID=12345\r
                """;
        final byte[] rawHttpRequest = rawHttpRequestString.getBytes(StandardCharsets.UTF_8);
        final InputStream requestInputStream = new ByteArrayInputStream(rawHttpRequest);

        final HttpRequest sut = new HttpRequest(requestInputStream);

        // when
        final HttpSession httpSession = sut.getSession(false);

        // then
        assertNotNull(httpSession);
    }
}