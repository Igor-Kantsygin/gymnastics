package com.gymnastics.service;

import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class AdminService {

    private final Set<Long> admins = Set.of(6336258822L);

    public boolean isAdmin(Long chatId) {
        //return admins.contains(chatId);
        return true;
    }
}
