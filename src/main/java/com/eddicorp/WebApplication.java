package com.eddicorp;

import com.eddicorp.tomcatapp.controller.MainServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

public class WebApplication {

    public static void main(String[] args) throws Exception {
        final Server server = new Server(8080);

        final ServletHandler servletHandler = new ServletHandler();
        servletHandler.addServletWithMapping(MainServlet.class, "/");

        server.setHandler(servletHandler);

        // start server
        server.start();
        server.join();
    }
}
