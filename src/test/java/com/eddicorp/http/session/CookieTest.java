package com.eddicorp.http.session;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CookieTest {
    @DisplayName("Cookie.toString()이 올바른 값의 String을 반환한다.")
    @Test
    void test1() {
        // given
        final Cookie sut = new Cookie("testName", "testValue");

        // when
        final String expected = "Cookie{name='testName', value='testValue'}";
        final String actual = sut.toString();

        // then
        assertEquals(expected, actual);
    }

}