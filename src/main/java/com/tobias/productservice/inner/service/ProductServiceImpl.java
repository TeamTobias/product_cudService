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

    public Iterable<Product> getProductsAll() {return productRepository.findAll();}

    public List<Product> getProductsAllBySaleActive(boolean saleActive){return productRepository.findAllBySaleActive(saleActive);}

    @Transactional
    public void addProduct(RequestProduct item) {

        List<Product> products = productRepository.findByItemId(item.getItemId());

        if(products.isEmpty()){
           for(String size : item.getSizes()){
               Product product = Product.createProduct(item);
               product.setSize(size);
               productRepository.save(product);
           }
        }
    }

    @Transactional
    public void deleteProduct(int itemId){
        productRepository.deleteByItemId(itemId);
    }

    public void setProductSale(int itemId, double saleRate){
        List<Product> products = productRepository.findByItemId(itemId);
        for(Product product : products){
            if(saleRate == 0)
                product.setSaleActive(false);
            else
                product.setSaleActive(true);
            product.setSaleRate(saleRate);
            productRepository.save(product);
        }
    }

    public void setProductCount(int id, int amount){
        Product product = productRepository.findById(id);
        product.setCount(amount);
        productRepository.save(product);
    }




}