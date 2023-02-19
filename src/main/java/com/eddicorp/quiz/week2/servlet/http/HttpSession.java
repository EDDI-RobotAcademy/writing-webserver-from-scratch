package com.eddicorp.quiz.week2.servlet.http;

/**
 * 세션을 표현합니다.
 */
public interface HttpSession {

    /**
     * @return 세션 ID
     */
    String getId();

    /**
     * @param intervalInSeconds 세션이 만료되는 시간을 지정합니다.
     */
    void setMaxInactiveInterval(int intervalInSeconds);

    /**
     * @return 세션이 만료되는 시간
     */
    int getMaxInactiveInterval();

    /**
     * @param name attribute 이름
     * @return attribute
     */
    Object getAttribute(String name);

    /**
     * @param name attribute 이름
     * @param value attribute
     */
    void setAttribute(String name, Object value);

    /**
     * @param name 제거할 attribute 이름
     */
    void removeAttribute(String name);

    /**
     * 세션 무효화
     */
    void invalidate();
}
