package com.vnikulshin.wb.service;


import jakarta.ws.rs.core.UriBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Service
public class SendTelegramService {

    @Value("${app.bot.token}")
    private String botToken;
    @Value("${app.bot.chat-id}")
    private String chatId;

    public String sendTelegram(String message) throws IOException, InterruptedException {


        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .version(HttpClient.Version.HTTP_2)
                .build();

        UriBuilder builder = UriBuilder
                .fromUri("https://api.telegram.org")
                .path("/{token}/sendMessage")
                .queryParam("chat_id", chatId)
                .queryParam("text", message);

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(builder.build("bot" + botToken))
                .timeout(Duration.ofSeconds(5))
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
    }


}
