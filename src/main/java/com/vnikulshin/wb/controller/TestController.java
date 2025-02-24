package com.vnikulshin.wb.controller;

import com.vnikulshin.wb.service.SendTelegramService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final SendTelegramService sendTelegramService;

    @GetMapping("/test")
    public String sendTelegram(@RequestParam("message") String message) throws IOException, InterruptedException {
        return sendTelegramService.sendTelegram(message);

    }
}
