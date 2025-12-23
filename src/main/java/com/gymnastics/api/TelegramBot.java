package com.gymnastics.api;

import com.gymnastics.api.admin.AdminPanel;
import com.gymnastics.api.client.ClientPanel;
import com.gymnastics.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class TelegramBot extends TelegramWebhookBot {

    @Value("${telegram.bot.username}")
    private String username;
    private final AdminPanel adminPanel;
    private final ClientPanel clientPanel;
    private final AdminService adminService;

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        Long chatId = extractChatId(update);
        if (chatId == null) return null;

        try {
            return adminService.isAdmin(chatId)
                    ? adminPanel.adminPanel(update, chatId)
                    : clientPanel.clientPanel(update, chatId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Long extractChatId(Update update) {
        if (update.hasMessage())
            return update.getMessage().getChatId();
        else  if (update.hasCallbackQuery())
            return update.getCallbackQuery().getMessage().getChatId();
        else return null;
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotPath() {
        return null;
    }
}
