package com.tobias.productservice.inner.domain;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class RequestProduct {
    private int id;
    private int itemId;
    private int salerId;
    private String name;
    private int price;
    private UUID imgUUID;
    private String color;
    private boolean saleActive;
    private double saleRate;

    List<String> sizes;
}
