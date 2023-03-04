package com.eddicorp.http;

import java.util.HashMap;
import java.util.Map;

public class DefaultHttpSession implements HttpSession {

    private final String id;

    private final Map<String, Object> map = new HashMap<>();

    public DefaultHttpSession(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setAttribute(String name, Object value) {
        map.put(name, value);
    }

    @Override
    public Object getAttribute(String name) {
        return map.get(name);
    }

    @Override
    public void removeAttribute(String name) {
        map.remove(name);
    }

    @Override
    public void invalidate() {
        SessionManager.removeSession(id);
    }

    @Override
    public String toString() {
        return "HttpSession{" +
                "id='" + id + '\'' +
                ", map=" + map +
                '}';
    }
}
