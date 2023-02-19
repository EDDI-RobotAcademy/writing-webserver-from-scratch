package com.eddicorp.quiz.week2.servlet.http;

import com.eddicorp.quiz.week2.servlet.ServletRequest;

/**
 * HTTP 요청을 표현하는 인터페이스입니다.
 */
public interface HttpServletRequest extends ServletRequest {

    /**
     * @return 현재 요청의 쿠키
     */
    Cookie[] getCookies();

    /**
     * @param name 헤더 이름
     * @return 헤더 값
     */
    String getHeader(String name);

    /**
     * @return HTTP method
     */
    String getMethod();

    /**
     * @return query string
     */
    String getQueryString();

    /**
     * @return 요청 URI
     */
    String getRequestURI();

    /**
     * @param create 세션을 새로 생성할지 말지 여부
     * @return HTTP 세션
     */
    HttpSession getSession(boolean create);

    /**
     * @return HTTP 세션
     */
    HttpSession getSession();
}
