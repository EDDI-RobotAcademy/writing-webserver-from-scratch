package com.eddicorp.examples.week2;

import com.eddicorp.quiz.week3.HttpRequest3;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("요청 객체에 대한 테스트")
public class test {

    @DisplayName("Get method로 uri 요청한 경우 url은 /")
    @Test
    void test1() throws IOException {
        final String rawHttpRequestString = "GET /?page=1 HTTP/1.1\r\n";
        final byte[] rawHttpRequest = rawHttpRequestString.getBytes(StandardCharsets.UTF_8);
        final InputStream requestInputStream = new ByteArrayInputStream(rawHttpRequest);

        final HttpRequest3 httpRequest3 = new HttpRequest3(requestInputStream);
        final String actual = httpRequest3.getUri();

        final String expected = "/";
        assertEquals(expected, actual);
    }

    @DisplayName("Get method와 uri /?page=1로 요청한 경우 파라미터 값 호출 시 1이 나옴")
    @Test
    void test2() throws IOException {
        final String rawHttpRequestString =  "GET /?page=1 HTTP/1.1\r\n";
        final byte[] rawHttpRequest = rawHttpRequestString.getBytes(StandardCharsets.UTF_8);
        final InputStream requestInputStream = new ByteArrayInputStream(rawHttpRequest);

        final HttpRequest3 httpRequest3 = new HttpRequest3(requestInputStream);

        final String expect = "1";
        final String actual = httpRequest3.getParams("page");

        assertEquals(expect,actual);
    }

    @DisplayName("Get method uri /로 요청한 경우 getMethod가 호출되면 get을 반환")
    @Test
    void test3() throws IOException{
        final String rawHttpRequestString = "GET / HTTP/1.1\r\n";
        final byte[] rawHttpRequest = rawHttpRequestString.getBytes(StandardCharsets.UTF_8);
        final InputStream inputStream = new ByteArrayInputStream(rawHttpRequest);

        final HttpRequest3 httpRequest3 = new HttpRequest3(inputStream);

        final String expect = "GET";
        final String actual = httpRequest3.getMethod();

        assertEquals(expect, actual);
    }
}
