package com.eddicorp.quiz.week2.servlet;

import java.io.OutputStream;

/**
 * 서버의 응답을 표현하는 인터페이스입니다.
 */
public interface ServletResponse {

    /**
     * @return OutputStream
     */
    OutputStream getOutputStream();

    /**
     * 버퍼에 있는 컨텐츠를 강제로 클라이언트에 쓰기
     */
    void flushBuffer();

    /**
     * 버퍼의 내용을 비웁니다. 세팅한 응답 값들은 비우지 않습니다.
     */
    void resetBuffer();
}
