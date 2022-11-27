package com.tobias.productservice.outer.rest.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class product_cudController {
    @GetMapping("/health_check")
    public String status(){
        return "It's Working in Product CUD Service";
    }
}
