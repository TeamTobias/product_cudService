package com.tobias.productservice.outer.rest.web;

import com.tobias.productservice.inner.domain.RequestProduct;
import com.tobias.productservice.inner.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProductController{

        private final ProductService productService;

        @GetMapping("/health_check")
        public String status(){
            return "It's Working in Product CUD Service";
        }

        @PostMapping("/products/v1/{salerId}")
        public void addProduct(@PathVariable("salerId") int salerId, @RequestBody RequestProduct requestProduct){
            // 상품 등록
            productService.addProduct(salerId, requestProduct);
        }
}
