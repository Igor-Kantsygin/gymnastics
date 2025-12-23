package com.gymnastics.service;

import com.gymnastics.api.UserSession;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserSessionService {
    private final Map<Long, UserSession> sessions = new ConcurrentHashMap<>();

    public UserSession get(Long chatId) {
        return sessions.computeIfAbsent(chatId, id -> new UserSession());
    }

    public void clear(Long chatId) {
        sessions.remove(chatId);
    }
}
