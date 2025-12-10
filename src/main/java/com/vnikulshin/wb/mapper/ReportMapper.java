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
        if (wb != null  && !wb.getProducts().isEmpty()) {
            report.setId(wb.getProducts().get(0).getId());
            report.setCardId(wb.getProducts().get(0).getRoot());
            report.setOptionId(wb.getProducts().get(0).getSubjectId());
            report.setBrand(wb.getProducts().get(0).getBrand());
            report.setFullName(wb.getProducts().get(0).getName());
            report.setSupplier(wb.getProducts().get(0).getSupplier());
            report.setPrice(getPrice(wb));
            report.setPriceBeforeDiscount(getPriceBeforeDiscount(wb));
        }
        return report;
    }

    private Double getPrice(Waldberries wb) {
        Waldberries.Size size = wb.getProducts().get(0).getSizes()
                .stream().findFirst()
                .orElse(new Waldberries.Size());

        if (size.getPrice() != null) {
            return (double) (size.getPrice().getProduct() + size.getPrice().getLogistics()) / 100;

        }
        return 0.0;

    }

    private Double getPriceBeforeDiscount(Waldberries wb) {
        Waldberries.Size size = wb.getProducts().get(0).getSizes()
                .stream().findFirst()
                .orElse(new Waldberries.Size());

        if (size.getPrice() != null) {
            return (double) (size.getPrice().getBasic()) / 100;

        }
        return 0.0;

    }

}
