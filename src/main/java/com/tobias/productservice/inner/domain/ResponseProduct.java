package com.tobias.productservice.inner.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class ResponseProduct {
    private int id;
    private int itemId;
    private int salerId;
    private String name;
    private int price;
    private UUID imgUUID;
    private String size;
    private String color;
    private int count;
    private boolean saleActive;
    private double saleRate;
}
