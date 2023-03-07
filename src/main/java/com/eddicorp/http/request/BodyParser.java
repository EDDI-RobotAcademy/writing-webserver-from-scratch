package com.eddicorp.http.request;

import java.util.HashMap;
import java.util.Map;

public class BodyParser {

    public Map<String, String> mappedBody = new HashMap<>();

    public Map<String, String> getMappedBody() {
        return mappedBody;
    }

    public BodyParser(String body) {
        String[] bodyArray = body.split("&");
        for (String bodyData : bodyArray) {
            String[] keyAndValue = bodyData.split("=");
            this.mappedBody.put(keyAndValue[0], keyAndValue[1]);
        }
    }
}
