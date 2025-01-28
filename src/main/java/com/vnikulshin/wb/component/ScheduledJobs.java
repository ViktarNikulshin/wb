package com.vnikulshin.wb.component;

import com.vnikulshin.wb.service.ParserService;
import com.vnikulshin.wb.service.SheetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Slf4j
@Component
@RequiredArgsConstructor
public class ScheduledJobs {
    private final ParserService parserService;
    private final SheetService sheetService;

    @Scheduled(fixedRate = 30000)
    public void updatePrice() throws IOException, GeneralSecurityException {
        log.info("Updating price...");
        parserService.parserWB();
    }


//    @Scheduled(fixedRate = 5000)
//    public void getSheell() throws IOException, GeneralSecurityException {
//        log.info("Updating s...");
//        sheetService.getSheet();
//    }
}
