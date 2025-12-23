package com.gymnastics.api.admin;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

import static com.gymnastics.util.CreateButton.getIKMButton;

@Service
@Getter
@Setter
public class AdminKeyboard {

    private InlineKeyboardMarkup ikmMainAdminMenu;

    private InlineKeyboardMarkup ikmMainAdminCancel;

    public AdminKeyboard() {
        ikmMainAdminMenu = createIkmAdminMenu();
        ikmMainAdminCancel = createIkmAdminCancel();
    }

    // Главное меню администратора
    private InlineKeyboardMarkup createIkmAdminMenu() {
        List<List<InlineKeyboardButton>> itemsAdminMenu = new ArrayList<>();
        InlineKeyboardMarkup inlineKeyboardMarkupStart = new InlineKeyboardMarkup();
        itemsAdminMenu.add(getIKMButton("save_request","Занести заявку"));
        List<List<InlineKeyboardButton>> inlineKeysStart = new ArrayList<>(itemsAdminMenu);
        inlineKeyboardMarkupStart.setKeyboard(inlineKeysStart);
        return inlineKeyboardMarkupStart;
    }

    private InlineKeyboardMarkup createIkmAdminCancel() {
        List<List<InlineKeyboardButton>> itemsAdminCancel = new ArrayList<>();
        InlineKeyboardMarkup inlineKeyboardMarkupStart = new InlineKeyboardMarkup();
        itemsAdminCancel.add(getIKMButton("cancel","Отмена"));
        List<List<InlineKeyboardButton>> inlineKeysStart = new ArrayList<>(itemsAdminCancel);
        inlineKeyboardMarkupStart.setKeyboard(inlineKeysStart);
        return inlineKeyboardMarkupStart;
    }
}
