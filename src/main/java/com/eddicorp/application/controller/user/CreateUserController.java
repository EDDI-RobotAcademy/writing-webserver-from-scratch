package com.eddicorp.application.controller.user;

import com.eddicorp.application.controller.Controller;
import com.eddicorp.http.request.BodyParser;
import com.eddicorp.http.request.HttpRequest;
import com.eddicorp.http.response.HttpResponse;

import java.io.IOException;
import java.util.Map;

public class CreateUserController implements Controller {
    @Override
    public void handle(HttpRequest request, HttpResponse response) throws IOException {
        for (String key : request.getHeaderMap().keySet()) {
            System.out.println("key: " + key +"\n" + "value: " + request.getHeaderMap().get(key));
        }

        BodyParser bodyParser = new BodyParser(request.getBody());
        Map<String, String> mappedBody = bodyParser.mappedBody;
        for (String s : mappedBody.keySet()) {
            System.out.println("key: " + s);
            System.out.println("value: "+mappedBody.get(s));
        }
    }
}
