package com.eddicorp.http.session;

public interface HttpSession {
    String getId();

    void setAttribute(String name, Object object);

    Object getAttribute(String name);

    void removeAttribute(String name);

    void invalidate();
}
