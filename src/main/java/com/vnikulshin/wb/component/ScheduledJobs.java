package com.vnikulshin.wb.component;

import com.vnikulshin.wb.service.ParserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class ScheduledJobs {
    private final ParserService parserService;

    @Scheduled(fixedRateString = "${app.job.parser.rate}")
    public void updatePrice() throws IOException, IllegalAccessException, InterruptedException {
        log.info("Updating price...");
        parserService.parserWB();
    }

}
