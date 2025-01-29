package com.vnikulshin.wb.service;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vnikulshin.wb.mapper.ReportMapper;
import com.vnikulshin.wb.model.Report;
import com.vnikulshin.wb.model.Waldberries;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ParserService {
    private final ReportMapper reportMapper;
    private final SheetService sheetService;
    private static final String BASE_URL = "https://card.wb.ru/cards/v2/detail?appType=1&curr=byn&dest=-59202&spp=30&hide_dtype=10&ab_testing=false&nm=";

    public void parserWB() throws IOException, GeneralSecurityException, IllegalAccessException {

        List<String> links = sheetService.getSheet();
        List<Report> reports = new ArrayList<>();

        for (String id : links) {

            URL obj = new URL(BASE_URL + id);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "PostmanRuntime/7.28.2");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder resp = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                resp.append(inputLine);
            }
            in.close();
            ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Waldberries wb = mapper.readValue(resp.toString(), Waldberries.class);
            Report report = reportMapper.getReport(wb);
            if (report.getId() != null) {
                reports.add(report);
            } else {
                log.info("Product not found - {}", id);
                report.setId(Integer.parseInt(id));
                reports.add(report);
            }
        }
        sheetService.setSheet(reports);
    }
}
