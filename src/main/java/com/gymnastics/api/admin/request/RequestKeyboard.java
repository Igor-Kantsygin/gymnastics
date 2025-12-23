package com.gymnastics.api.admin.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

import static com.gymnastics.util.CreateButton.getIKMButton;

@Getter
@Setter
@Service
public class RequestKeyboard {

    // Заявка
    InlineKeyboardMarkup ikmRequest;

    // Филиалы
    InlineKeyboardMarkup ikmAffiliate;

    public RequestKeyboard() {
        ikmRequest = createIkmRequest();
        ikmAffiliate = createIkmAffiliate();
    }

    // Заявка
    private InlineKeyboardMarkup createIkmRequest() {
        List<List<InlineKeyboardButton>> itemsRequest = new ArrayList<>();
        InlineKeyboardMarkup inlineKeyboardMarkupStart = new InlineKeyboardMarkup();
        itemsRequest.add(getIKMButton("addAffiliate","Занести заявку"));
        List<List<InlineKeyboardButton>> inlineKeysStart = new ArrayList<>(itemsRequest);
        inlineKeyboardMarkupStart.setKeyboard(inlineKeysStart);
        return inlineKeyboardMarkupStart;
    }

    // Филиалы
    private InlineKeyboardMarkup createIkmAffiliate() {
        List<List<InlineKeyboardButton>> itemsAffiliate = new ArrayList<>();
        InlineKeyboardMarkup inlineKeyboardMarkupStart = new InlineKeyboardMarkup();
        itemsAffiliate.add(getIKMButton("magas", "г.Магас"));
        itemsAffiliate.add(getIKMButton("dvorec", "г.Назрань, Дворец спорта"));
        itemsAffiliate.add(getIKMButton("zaKanalom", "г.Назрань, за каналом"));
        itemsAffiliate.add(getIKMButton("karabulak", "г.Карабулак"));
        itemsAffiliate.add(getIKMButton("abarg", "с.п.Троицкое"));
        itemsAffiliate.add(getIKMButton("malgobek", "г.Малгобек"));
        List<List<InlineKeyboardButton>> inlineKeysStart = new ArrayList<>(itemsAffiliate);
        inlineKeyboardMarkupStart.setKeyboard(inlineKeysStart);
        return inlineKeyboardMarkupStart;
    }
}
