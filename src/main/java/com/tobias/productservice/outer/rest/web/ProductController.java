package com.tobias.productservice.outer.rest.web;

import com.tobias.productservice.inner.domain.Product;
import com.tobias.productservice.inner.domain.RequestProduct;
import com.tobias.productservice.inner.domain.ResponseProduct;
import com.tobias.productservice.inner.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController{

        private final ProductService productService;

        @GetMapping("/health_check")
        public String status(){
            return "It's Working in Product CUD Service";
        }

        @PostMapping("/product/v1")
        public void addProduct(@RequestBody RequestProduct requestProduct){
            // 상품 등록
            productService.addProduct(requestProduct);
        }

        @GetMapping("/product/v1")
        public ResponseEntity<List<ResponseProduct>> allProduct() {
            Iterable<Product> products = productService.getProductsAll();
            List<ResponseProduct> result = new ArrayList<>();
            products.forEach(v -> result.add(new ModelMapper().map(v, ResponseProduct.class)));
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
        @DeleteMapping("/product/v1/{itemId}")
        public void deleteProduct(@PathVariable("itemId") int itemId){
            productService.deleteProduct(itemId);
        }

        @PostMapping("/product/v1/{itemId}/{saleRate}")
        public void setProductSale(@PathVariable("itemId") int itemId,@PathVariable("saleRate") int saleRate){
            productService.setProductSale(itemId,saleRate);
        }

        @GetMapping("/product/v1/sale")
        public ResponseEntity<List<ResponseProduct>> allSaleProduct() {
            Iterable<Product> products = productService.getProductsAllBySaleActive(true);
            List<ResponseProduct> result = new ArrayList<>();
            products.forEach(v -> result.add(new ModelMapper().map(v, ResponseProduct.class)));
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }

        @PutMapping("/product/v1/{productId}/{amount}")
        public void setProductCount(@PathVariable("productId") int productId,@PathVariable("amount") int amount){
            productService.setProductCount(productId,amount);
        }
}
