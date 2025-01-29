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
        if (wb != null && wb.getProductData() != null && !wb.getProductData().getProducts().isEmpty()) {
            report.setId(wb.getProductData().getProducts().get(0).getId());
            report.setCardId(wb.getProductData().getProducts().get(0).getRoot());
            report.setOptionId(wb.getProductData().getProducts().get(0).getSubjectId());
            report.setBrand(wb.getProductData().getProducts().get(0).getBrand());
            report.setFullName(wb.getProductData().getProducts().get(0).getName());
            report.setSupplier(wb.getProductData().getProducts().get(0).getSupplier());
            report.setPrice(getPrice(wb));
            report.setPriceBeforeDiscount(getPriceBeforeDiscount(wb));
        }
        return report;
    }

    private Double getPrice(Waldberries wb) {
        Waldberries.Size size = wb.getProductData().getProducts().get(0).getSizes()
                .stream().findFirst()
                .orElse(new Waldberries.Size());

        if (size.getPrice() != null) {
            return (double) (size.getPrice().getProduct()) / 100;

        }
        return 0.0;

    }

    private Double getPriceBeforeDiscount(Waldberries wb) {
        Waldberries.Size size = wb.getProductData().getProducts().get(0).getSizes()
                .stream().findFirst()
                .orElse(new Waldberries.Size());

        if (size.getPrice() != null) {
            return (double) (size.getPrice().getBasic()) / 100;

        }
        return 0.0;

    }

}
