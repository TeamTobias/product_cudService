package com.tobias.productservice.inner.repository;

import com.tobias.productservice.inner.domain.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    Product findById(int id);
    List<Product> findByItemId(int itemId);
    void deleteByItemId(int itemId);
    List<Product> findAllBySaleActive(boolean saleActive);
}
