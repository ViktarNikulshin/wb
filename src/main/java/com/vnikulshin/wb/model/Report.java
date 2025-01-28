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
    private Integer qty;
    private Integer totalDeliveryTimeInDays;
    private Integer	brandId;
    private Integer supplierId;
    private Integer whId;
    private Integer assemblyTime;
    private Integer courierDeliveryTime;
    private Integer totalDeliveryTime;
}
