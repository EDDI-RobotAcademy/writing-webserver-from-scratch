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
//        sut.setHeader("Content-Type", "application/json");
//        sut.setBody("Test body".getBytes(StandardCharsets.UTF_8));

        sut.flush();


        // when
        String expected = "HTTP/1.1 200 OK\r\n\r\n";
        String actual = outputStream.toString(StandardCharsets.UTF_8);

//        String expectedHeaders = "Content-Type: application/json\r\n";
//        String expectedCRLF = "\r\n";
//        String expectedBody = "Test body";


        // then
        assertEquals(expected, actual);

    }

}