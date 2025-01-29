package com.vnikulshin.wb.model;

import lombok.Data;

@Data
public class Report {
    private Integer id;
    private Integer cardId;
    private Integer optionId;
    private String brand;
    private String fullName;
    private String supplier;
    private Double price;
    private Double priceBeforeDiscount;
}
