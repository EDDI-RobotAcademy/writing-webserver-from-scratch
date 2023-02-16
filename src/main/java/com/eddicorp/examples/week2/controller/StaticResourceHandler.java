package com.eddicorp.examples.week2.controller;

import org.eclipse.jetty.server.handler.ResourceHandler;

public class StaticResourceHandler extends ResourceHandler {

    public StaticResourceHandler() {
        super();
        this.setDirectoriesListed(false);
        this.setResourceBase("./src/main/resources/pages");
    }
}
