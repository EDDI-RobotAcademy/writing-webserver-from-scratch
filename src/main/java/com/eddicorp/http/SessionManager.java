package com.eddicorp.http;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class SessionManager {

    public static final String SESSION_KEY_NAME = "JSESSIONID";
    private static final Map<String, HttpSession> sessionRepository = new HashMap<>();

    public static void removeSession(String id) {
        sessionRepository.remove(id);
    }

    public static String createNewSession() {
        final HttpSession session = createSession(UUID.randomUUID().toString());
        return session.getId();
    }

    private static HttpSession createSession(String id) {
        final HttpSession newSession = new DefaultHttpSession(id);
        sessionRepository.put(id, newSession);
        return newSession;
    }

    public static HttpSession getSession(String id) {
        final HttpSession maybeHttpSession = sessionRepository.get(id);
        if (maybeHttpSession == null) {
            return createSession(id);
        }
        return maybeHttpSession;
    }
}
