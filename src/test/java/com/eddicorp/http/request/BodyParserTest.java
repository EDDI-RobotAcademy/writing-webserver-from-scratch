package com.eddicorp.http.request;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class BodyParserTest {

    @DisplayName("hashMap with title and content is returned when requestBody is title=112&content=113")
    @Test
    void test1() {
        // given
        final String requestBodyString = "title=112&content=113";
        final BodyParser sut = new BodyParser(requestBodyString);

        // when
        final Map<String, String> expectedMap = new HashMap<>();
        expectedMap.put("title", "112");
        expectedMap.put("content", "113");
        final Map<String, String> actual = sut.getMappedBody();

        // then
        assertEquals(expectedMap, actual);
    }
}