package com.tobias.productservice.inner.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
