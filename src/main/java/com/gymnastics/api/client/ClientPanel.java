package com.gymnastics.api.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class ClientPanel {

    private final ClientKeyboard clientKeyboard;

    public BotApiMethod<?> clientPanel(Update update, Long chatId) {
        return SendMessage.builder()
                .text("Клиент")
                .chatId(chatId)
                .build();
    }
}
