package com.eddicorp.quiz.week2.servlet;

/**
 * 서블릿을 나타내는 인터페이스입니다.
 * 서블릿은 원론적으로는 client-server 프로토콜로 통신이 가능합니다.
 * 하지만 가장 일반적인 사용은 HTTP 프로토콜입니다.
 */
public interface Servlet {

    void service(ServletRequest req, ServletResponse res);
}
