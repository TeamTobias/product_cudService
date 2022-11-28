package com.tobias.productservice.inner.repository;

import com.tobias.productservice.inner.domain.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
    Product findById(int id);
}
