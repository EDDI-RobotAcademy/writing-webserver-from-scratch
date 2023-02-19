package com.eddicorp.quiz.week2.server;

import com.eddicorp.quiz.week2.servlet.Servlet;

/**
 *
 */
public class Server {

    private int port;

    public Server(int port) {
        this.port = port;
    }

    public void start() {
        throw new IllegalStateException("Not implemented yet");
    }

    public <T extends Servlet> void addServletWithMapping(T servlet, String path) {
        throw new IllegalStateException("Not implemented yet");
    }

    public <T extends Servlet> void addStaticResourceServlet(T servlet, String path) {
        throw new IllegalStateException("Not implemented yet");
    }
}
