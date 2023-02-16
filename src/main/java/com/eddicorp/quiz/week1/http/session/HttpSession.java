package com.eddicorp.quiz.week1.http.session;

public interface HttpSession {
    String getId();

    void setAttribute(String name, Object object);

    Object getAttribute(String name);

    void removeAttribute(String name);

    void invalidate();
}
