package com.gymnastics.api.admin.request;

import com.gymnastics.api.UserSession;
import com.gymnastics.api.admin.AdminKeyboard;
import com.gymnastics.dto.request.RequestDraft;
import com.gymnastics.dto.request.RequestState;
import com.gymnastics.model.Request;
import com.gymnastics.service.SheetService;
import com.gymnastics.service.UserSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class RequestHandler {

    private final UserSessionService sessionService;
    private final AdminKeyboard adminKeyboard;
    private final SheetService sheetService;

    public SendMessage handle(Update update, Long chatId) throws IOException {

        UserSession session = sessionService.get(chatId);
        RequestDraft draft = session.getRequestDraft();

        String text = update.hasMessage()
                ? update.getMessage().getText()
                : null;

        switch (session.getRequestState()) {

            case SELECT_BRANCH -> {
                draft.setBranch(text);
                session.setRequestState(RequestState.ENTER_FULL_NAME);
                return SendMessage.builder()
                        .text("Введите ФИО:")
                        .chatId(chatId)
                        .replyMarkup(adminKeyboard.getIkmMainAdminCancel())
                        .build();
            }

            case ENTER_FULL_NAME -> {
                draft.setFullName(text);
                session.setRequestState(RequestState.ENTER_PHONE);
                return SendMessage.builder()
                        .text("Введите номер телефона:")
                        .chatId(chatId)
                        .replyMarkup(adminKeyboard.getIkmMainAdminCancel())
                        .build();
            }

            case ENTER_PHONE -> {
                draft.setPhone(text);
                session.setRequestState(RequestState.SELECT_STATUS);
                return SendMessage.builder()
                        .text("Введите статус заявки:")
                        .chatId(chatId)
                        .replyMarkup(adminKeyboard.getIkmMainAdminCancel())
                        .build();
            }

            case SELECT_STATUS -> {
                draft.setStatus(text);
                session.setRequestState(RequestState.ENTER_COMMENT);
                return SendMessage.builder()
                        .text("Введите примечание:")
                        .chatId(chatId)
                        .replyMarkup(adminKeyboard.getIkmMainAdminCancel())
                        .build();
            }

            case ENTER_COMMENT -> {
                draft.setComment(text);
                session.setRequestState(RequestState.COMPLETED);
                Request request = new Request(
                        draft.getBranch(),
                        draft.getFullName(),
                        draft.getPhone(),
                        draft.getStatus(),
                        draft.getComment()
                );
                sheetService.saveApplication(request);

                sessionService.clear(chatId);

                return SendMessage.builder()
                        .text("Заявка сохранена ✅" +
                                "\nГлавное меню")
                        .chatId(chatId)
                        .replyMarkup(adminKeyboard.getIkmMainAdminMenu())
                        .build();
            }

            default -> {
                sessionService.clear(chatId);
                return new SendMessage(chatId.toString(), "Произошла ошибка. Начните заново.");
            }
        }
    }
}
