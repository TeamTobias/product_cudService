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

    public static Product createProduct(int salerId, RequestProduct requestProduct) {
        Product product = new Product();
        product.setSalerId(salerId);
        product.setItemId(requestProduct.getItemId());
        product.setName(requestProduct.getName());
        product.setPrice(requestProduct.getPrice());
        product.setImgUUID(requestProduct.getImgUUID());
        product.setColor(requestProduct.getColor());
        product.setCount(0);
        product.setSaleActive(requestProduct.isSaleActive());
        product.setSaleRate(requestProduct.getSaleRate());
        return product;
    }
}
