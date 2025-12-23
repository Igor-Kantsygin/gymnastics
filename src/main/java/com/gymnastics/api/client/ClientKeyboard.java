package com.gymnastics.api.client;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

import static com.gymnastics.util.CreateButton.getIKMButton;

@Service
public class ClientKeyboard {

    InlineKeyboardMarkup ikmMainClient;

    public ClientKeyboard() {
        ikmMainClient = createIkmClient();
    }

    // Главное меню клиента
    private InlineKeyboardMarkup createIkmClient() {
        List<List<InlineKeyboardButton>> itemsClient = new ArrayList<>();
        InlineKeyboardMarkup inlineKeyboardMarkupStart = new InlineKeyboardMarkup();
        itemsClient.add(getIKMButton("main_client","Главное меню"));
        List<List<InlineKeyboardButton>> inlineKeysStart = new ArrayList<>(itemsClient);
        inlineKeyboardMarkupStart.setKeyboard(inlineKeysStart);
        return inlineKeyboardMarkupStart;
    }
}
