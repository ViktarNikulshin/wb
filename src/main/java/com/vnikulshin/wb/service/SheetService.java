package com.vnikulshin.wb.service;

import com.google.api.services.sheets.v4.model.ValueRange;
import com.vnikulshin.wb.util.SheetsServiceUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class SheetService {
    private final SheetsServiceUtil sheetsService;
    private final String ID_SHEET = "1DLfnYlh5UQTriF4ApZ9k7_Uj2oSr-Ql0m8jlnevBYLg";


    public List<String> getSheet() throws IOException, GeneralSecurityException {
        ValueRange readResult = SheetsServiceUtil.getSheetsService().spreadsheets().values()
                .get(ID_SHEET, "links")
                .execute();
        List<List<Object>> values = readResult.getValues();
        values.forEach(l -> log.info(l.toString()));

        return values.stream().map(l ->l.get(0).toString()).collect(Collectors.toList());
    }
}
