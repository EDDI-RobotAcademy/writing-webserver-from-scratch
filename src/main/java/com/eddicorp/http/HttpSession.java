package com.eddicorp.http;

public interface HttpSession {
    String getId();

    void setAttribute(String name, Object object);

    Object getAttribute(String name);

    void removeAttribute(String name);

    void invalidate();
}
