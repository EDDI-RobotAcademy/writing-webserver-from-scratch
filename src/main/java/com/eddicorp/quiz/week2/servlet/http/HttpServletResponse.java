package com.eddicorp.quiz.week2.servlet.http;

import com.eddicorp.quiz.week2.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;

/**
 * HTTP 응답을 표현하는 인터페이스입니다.
 */
public interface HttpServletResponse extends ServletResponse {

    /**
     * @param cookie 응답에 보낼 쿠키를 추가합니다
     */
    void addCookie(Cookie cookie);

    /**
     * @param httpStatus 응답 상태
     * @param errorMessage 에러 메세지
     */
    void sendError(HttpStatus httpStatus, String errorMessage);

    /**
     * @param location 리다이렉트 시 Location 헤더에 설정할 값을 추가합니다.
     */
    void sendRedirect(String location);

    /**
     * @param name 헤더 이름
     * @param value 헤더 값
     */
    void setHeader(String name, String value);

    /**
     * @param httpStatus 상태 코드를 설정합니다.
     */
    void setStatus(HttpStatus httpStatus);

    /**
     * @return 현재 설정되어 있는 HTTP 상태를 반환합니다.
     */
    HttpStatus getStatus();

    /**
     * @param name 헤더 이름
     * @return 헤더 값
     */
    String getHeader(String name);
}