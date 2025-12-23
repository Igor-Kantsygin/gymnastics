package com.gymnastics.api.admin;

import com.gymnastics.api.UserSession;
import com.gymnastics.api.admin.request.RequestHandler;
import com.gymnastics.service.UserSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AdminPanel {

    private final AdminKeyboard adminKeyboard;
    private final RequestHandler requestHandler;
    private final UserSessionService userSessionService;

    public BotApiMethod<?> adminPanel(Update update, Long chatId) throws IOException {

        UserSession session = userSessionService.get(chatId);

        if (update.hasCallbackQuery()) {

            // 1️⃣ ЯВНОЕ начало заявки
            if (update.getCallbackQuery().getData().equals("save_request")) {
                session.startRequest();
                return SendMessage.builder()
                        .text("Выберите филиал:")
                        .chatId(chatId)
                        .replyMarkup(adminKeyboard.getIkmMainAdminCancel())
                        .build();
            }

            // Отмена
            if (update.getCallbackQuery().getData().equals("cancel")) {
                session.finishRequest();
                return SendMessage.builder()
                        .text("Главное меню")
                        .chatId(chatId)
                        .replyMarkup(adminKeyboard.getIkmMainAdminMenu())
                        .build();
            }
        }

        // 2️⃣ Продолжение заявки
        if (session.isRequestActive()) {
            return requestHandler.handle(update, chatId);
        }

        // 3️⃣ Всё остальное — не заявка
        return SendMessage.builder()
                .text("Главное меню")
                .chatId(chatId)
                .replyMarkup(adminKeyboard.getIkmMainAdminMenu())
                .build();
    }

}
