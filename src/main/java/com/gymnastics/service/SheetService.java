package com.gymnastics.service;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.gymnastics.model.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class SheetService {

    private static final String RANGE = "Лист1!A:F";
    private final Sheets sheets;
    private final String spreadsheetId;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public SheetService(Sheets sheets,
                        @Value("${google.sheets.spreadsheet-id}") String spreadsheetId) {
        this.sheets = sheets;
        this.spreadsheetId = spreadsheetId;
    }

    @Async
    public void saveApplication(Request request) throws IOException {
        List<Object> row = List.of(
                LocalDate.now().format(formatter),
                request.getBranch(),
                request.getFullName(),
                request.getPhoneNumber(),
                request.getStatus(),
                request.getNotes()
        );

        ValueRange body = new ValueRange()
                .setValues(List.of(row));

        sheets.spreadsheets().values()
                .append(spreadsheetId, RANGE, body)
                .setValueInputOption("RAW")
                .setInsertDataOption("INSERT_ROWS")
                .execute();
    }
}
