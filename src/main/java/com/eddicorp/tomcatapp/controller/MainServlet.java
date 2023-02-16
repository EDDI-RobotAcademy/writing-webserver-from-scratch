package com.eddicorp.tomcatapp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.jetty.http.HttpHeader;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp
    ) throws ServletException, IOException {
        final String html = "<!DOCTYPE html>\n" +
                "<html lang=\"ko\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>제목</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>그만해</h1>\n" +
                "<p>으어어어어</p>\n" +
                "</body>\n" +
                "</html>";
        final int contentLength = html.getBytes(StandardCharsets.UTF_8).length;
        resp.setHeader(HttpHeader.CONTENT_LENGTH.asString(), String.valueOf(contentLength));
        resp.setHeader(HttpHeader.CONTENT_TYPE.asString(), "text/html;utf-8");
        resp.setCharacterEncoding("UTF-8");
        resp.getOutputStream().println(html);
    }
}
