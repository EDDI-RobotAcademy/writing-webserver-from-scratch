package com.eddicorp.quiz.week2.servlet.http;

import java.util.Locale;

/**
 * 쿠키를 표현하는 객체입니다.
 */
public class Cookie {

    private String name;
    private String value;
    private String comment;
    private String domain;
    private int maxAge = -1;
    private String path;
    private boolean secure;
    private int version = 0;
    private boolean isHttpOnly = false;

    public void setComment(String purpose) {
        comment = purpose;
    }

    public String getComment() {
        return comment;
    }

    public void setDomain(String domain) {
        this.domain = domain.toLowerCase(Locale.ENGLISH); // IE allegedly needs this
    }

    public String getDomain() {
        return domain;
    }

    public void setMaxAge(int expiry) {
        maxAge = expiry;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setPath(String uri) {
        path = uri;
    }

    public String getPath() {
        return path;
    }

    public void setSecure(boolean flag) {
        secure = flag;
    }

    public boolean getSecure() {
        return secure;
    }

    public String getName() {
        return name;
    }

    public void setValue(String newValue) {
        value = newValue;
    }

    public String getValue() {
        return value;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int v) {
        version = v;
    }

    public void setHttpOnly(boolean isHttpOnly) {
        this.isHttpOnly = isHttpOnly;
    }

    public boolean isHttpOnly() {
        return isHttpOnly;
    }
}
