package com.eddicorp.quiz.week2.servlet;

import java.io.IOException;
import java.io.InputStream;

/**
 * 클라이언트의 요청을 표현하는 인터페이스입니다.
 */
public interface ServletRequest {

    /**
     * @return 요청의 InputStream을 반환합니다.
     * @throws IOException
     */
    InputStream getInputStream() throws IOException;

    /**
     * queryString, form의 데이터를 조회합니다.
     * @param name 파라미터 이름
     * @return 값
     */
    String getParameter(String name);
}
