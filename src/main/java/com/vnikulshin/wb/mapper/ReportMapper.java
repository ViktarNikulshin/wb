package com.vnikulshin.wb.mapper;

import com.vnikulshin.wb.model.Report;
import com.vnikulshin.wb.model.Waldberries;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ReportMapper {

    public Report getReport(Waldberries wb) {
        Report report = new Report();
        if (wb != null && wb.getProductData()!= null) {
            report.setId(wb.getProductData().getProducts().get(0).getId());

        }
        return report;
    }

}
