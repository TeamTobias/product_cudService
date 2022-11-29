package com.tobias.productservice.inner.service;

import com.tobias.productservice.inner.domain.Product;
import com.tobias.productservice.inner.domain.RequestProduct;

import java.util.List;

public interface ProductService {
    void addProduct(RequestProduct product);
    Iterable<Product> getProductsAll();
    void deleteProduct(int itemId);
    void setProductSale(int itemId,double saleRate);
    List<Product> getProductsAllBySaleActive(boolean saleActive);
    void setProductCount(int id, int amount);
}
