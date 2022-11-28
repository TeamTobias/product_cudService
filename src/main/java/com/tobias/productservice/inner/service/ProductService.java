package com.tobias.productservice.inner.service;

import com.tobias.productservice.inner.domain.RequestProduct;

public interface ProductService {
    void addProduct(int salerId, RequestProduct product);

}
