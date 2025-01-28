package com.vnikulshin.wb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Waldberries {
    private int state;
    private int payloadVersion;
    @JsonProperty("data")
    private ProductData productData;

    @Data
    public static class Color {
        private String name;
        private int id;
    }

    @Data
    public static class ProductData {
        private List<Product> products;
    }

    @Data
    public static class Price {
        private int basic;
        private int product;
        private int total;
        private int logistics;
        @JsonProperty("return")
        private int myreturn;
    }

    @Data
    public static class Product {
        private int id;
        private int root;
        private int kindId;
        private String brand;
        private int brandId;
        private int siteBrandId;
        private List<Color> colors;
        private int subjectId;
        private int subjectParentId;
        private String name;
        private String entity;
        private int matchId;
        private String supplier;
        private int supplierId;
        private double supplierRating;
        private int supplierFlags;
        private int pics;
        private int rating;
        private double reviewRating;
        private int nmReviewRating;
        private int feedbacks;
        private int nmFeedbacks;
        private int volume;
        private int viewFlags;
        private List<Integer> promotions;
        private List<Size> sizes;
        private int totalQuantity;
        private int time1;
        private int time2;
        private int wh;
        private int dtype;
    }

    @Data
    public static class Size {
        private String name;
        private String origName;
        private int rank;
        private int optionId;
        private List<Stock> stocks;
        private int time1;
        private int time2;
        private int wh;
        private int dtype;
        private Price price;
        private int saleConditions;
        private String payload;
    }

    @Data
    public static class Stock {
        private int wh;
        private int dtype;
        private int qty;
        private int priority;
        private int time1;
        private int time2;
    }


}
