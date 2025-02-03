package com.vnikulshin.wb.service;

import com.google.api.services.sheets.v4.model.ClearValuesRequest;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.vnikulshin.wb.model.Report;
import com.vnikulshin.wb.util.SheetsServiceUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Field;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class SheetService {
    @Value("${app.exel.sheet}")
    private String SHEET_ID;
    @Value("${app.exel.list-in}")
    private String LIST_IN;
    @Value("${app.exel.list-out}")
    private String LIST_OUT;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public List<String> getSheet() throws IOException, GeneralSecurityException {
        ValueRange readResult = SheetsServiceUtil.getSheetsService().spreadsheets().values()
                .get(SHEET_ID, LIST_IN)
                .execute();
        List<List<Object>> values = readResult.getValues();
        return values.stream().map(l -> l.get(0).toString()).collect(Collectors.toList());
    }

    public void setSheet(List<Report> reports) throws IOException, GeneralSecurityException, IllegalAccessException {
        List<List<Object>> values = new ArrayList<>();
        Class<Report> outputClass = Report.class;
        Field[] fields = outputClass.getDeclaredFields();
        List<Object> value = new ArrayList<>(Arrays.stream(fields).map(Field::getName).toList());
        values.add(value);
        values.add(List.of(dateFormat.format(new Date())));
        for (Report r : reports) {
            List<Object> reportToString = new ArrayList<>();
            for (Field f : outputClass.getDeclaredFields()) {
                f.setAccessible(true);
                reportToString.add(f.get(r) != null ? f.get(r) : "");
            }
            values.add(reportToString);
        }
        SheetsServiceUtil.getSheetsService()
                .spreadsheets()
                .values()
                .clear(SHEET_ID, LIST_OUT, new ClearValuesRequest())
                .execute();

        ValueRange body = new ValueRange()
                .setValues(values);
        SheetsServiceUtil.getSheetsService().spreadsheets().values()
                .update(SHEET_ID, LIST_OUT, body)
                .setValueInputOption("RAW")
                .execute();
    }
}
