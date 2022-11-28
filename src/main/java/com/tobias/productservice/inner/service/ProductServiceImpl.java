package com.tobias.productservice.inner.service;

import com.tobias.productservice.inner.domain.Product;
import com.tobias.productservice.inner.domain.RequestProduct;
import com.tobias.productservice.inner.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public void addProduct(int salerId, RequestProduct item) {

        List<Product> products = productRepository.findByItemId(item.getId());

        if(products.isEmpty()){
           for(String size : item.getSizes()){
               Product product = Product.createProduct(salerId, item);
               product.setSize(size);
               productRepository.save(product);
           }
        }
    }


}