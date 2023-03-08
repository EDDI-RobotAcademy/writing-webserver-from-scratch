package com.eddicorp.application.controller;

import com.eddicorp.http.HttpMethod;
import com.eddicorp.http.HttpRequest;
import com.eddicorp.http.HttpResponse;

import java.util.HashMap;
import java.util.Map;

public class RootController implements Controller {
    private  static final String STATIC_FILE_PATH = "pages";
    private static final Map<RequestMapper, Controller> requestMap = new HashMap<>();

    static{
        final RequestMapper mapWritePost = new RequestMapper("/post", HttpMethod.POST);
        requestMap.put(mapWritePost, new WritePostApiController());
        final RequestMapper mapIndexPage = new RequestMapper("/", HttpMethod.GET);
        requestMap.put(mapIndexPage,new IndexController());
    }
    @Override
    public void handle(HttpRequest httpRequest, HttpResponse httpResponse) {

    }
}
