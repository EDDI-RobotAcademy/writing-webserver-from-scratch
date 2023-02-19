package com.eddicorp.quiz.week2.servlet.http;

import com.eddicorp.quiz.week2.servlet.Servlet;
import com.eddicorp.quiz.week2.servlet.ServletRequest;
import com.eddicorp.quiz.week2.servlet.ServletResponse;

/**
 * HTTP 요청에 대한 처리를 수행하는 서블릿입니다.
 * 웹 애플리케이션의 경우 이 서블릿을 반드시 구현해야 요청을 처리할 수 있습니다.
 */
public abstract class HttpServlet implements Servlet {

    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        httpServletResponse.sendError(HttpStatus.NOT_IMPLEMENTED, "Not Implemented");
    }

    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        httpServletResponse.sendError(HttpStatus.NOT_IMPLEMENTED, "Not Implemented");
    }

    protected void doHead(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        httpServletResponse.sendError(HttpStatus.NOT_IMPLEMENTED, "Not Implemented");
    }

    protected void doDelete(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        httpServletResponse.sendError(HttpStatus.NOT_IMPLEMENTED, "Not Implemented");
    }

    protected void doOptions(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        httpServletResponse.sendError(HttpStatus.NOT_IMPLEMENTED, "Not Implemented");
    }

    protected void doPut(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        httpServletResponse.sendError(HttpStatus.NOT_IMPLEMENTED, "Not Implemented");
    }

    protected void doTrace(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        httpServletResponse.sendError(HttpStatus.NOT_IMPLEMENTED, "Not Implemented");
    }

    protected void doConnect(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        httpServletResponse.sendError(HttpStatus.NOT_IMPLEMENTED, "Not Implemented");
    }

    protected void doPatch(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        httpServletResponse.sendError(HttpStatus.NOT_IMPLEMENTED, "Not Implemented");
    }

    protected boolean canHandle(HttpServletRequest request) {
        throw new IllegalStateException("Unable to determine request handling. Feature not implemented yet.");
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) {
        if (!(req instanceof HttpServletRequest && res instanceof HttpServletResponse)) {
            throw new IllegalArgumentException("non-HTTP request or response");
        }

        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;
        service(request, response);
    }

    protected void service(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        final String method = httpServletRequest.getMethod();
        final HttpMethod httpMethod = HttpMethod.valueOf(method);

        switch (httpMethod) {
            case OPTIONS:
                doOptions(httpServletRequest, httpServletResponse);
                break;
            case GET:
                doGet(httpServletRequest, httpServletResponse);
                break;
            case POST:
                doPost(httpServletRequest, httpServletResponse);
                break;
            case PUT:
                doPut(httpServletRequest, httpServletResponse);
                break;
            case DELETE:
                doDelete(httpServletRequest, httpServletResponse);
                break;
            case HEAD:
                doHead(httpServletRequest, httpServletResponse);
                break;
            case TRACE:
                doTrace(httpServletRequest, httpServletResponse);
                break;
            case CONNECT:
                doConnect(httpServletRequest, httpServletResponse);
                break;
            case PATCH:
                doPatch(httpServletRequest, httpServletResponse);
                break;
        }
    }
}
