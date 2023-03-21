package com.eddicorp.http.response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class ResponseCookieTest {
    @DisplayName("ResponseCookie.build() 실행 시 올바른 Cookie String을 반환한다.")
    @Test
    void test1() {
        // given
        final ResponseCookie sut = new ResponseCookie("JSESSIONID", "1234", "/", "localhost",
                60 * 3);

        // when
        final String expected = "JSESSIONID=1234; Path=/; Domain=localhost; Max-Age=180";
        final String actual = sut.build();

        // then
        assertEquals(expected, actual);
    }
}