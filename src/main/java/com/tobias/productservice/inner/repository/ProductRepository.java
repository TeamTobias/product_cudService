package com.tobias.productservice.inner.repository;

import com.tobias.productservice.inner.domain.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findByItemId(int id);
}
