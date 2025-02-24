package com.vnikulshin.wb.util;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.vnikulshin.wb.config.GoogleAuthorizeUtil;
import com.vnikulshin.wb.service.SendTelegramService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class SheetsServiceUtil {
    private static final String APPLICATION_NAME = "Google Sheets Example";
    private final SendTelegramService sendTelegramService;

    public Sheets getSheetsService() throws IOException, InterruptedException {
        try {
            Credential credential = GoogleAuthorizeUtil.authorize();
            return new Sheets.Builder(
                    GoogleNetHttpTransport.newTrustedTransport(),
                    JacksonFactory.getDefaultInstance(), credential)
                    .setApplicationName(APPLICATION_NAME)
                    .build();
        } catch (Exception e) {
            sendTelegramService.sendTelegram(e.getMessage());
            return null;
        }
    }
}
