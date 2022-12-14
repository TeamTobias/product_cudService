package com.tobias.productservice.outer.adaptor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tobias.productservice.inner.domain.Product;
import com.tobias.productservice.inner.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class KafkaConsumer {
    ProductRepository repository;

    @Autowired
    public KafkaConsumer(ProductRepository repository){
        this.repository = repository;
    }

    @KafkaListener(topics = "delivery-topic")
    public void processDelivery(String kafkaMessage){
        log.info("Kafka Message: ====> " + kafkaMessage);

        Map<Object,Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try{
            map = mapper.readValue(kafkaMessage, new TypeReference<Map<Object, Object>>() {});
            }catch(JsonProcessingException e){
            e.printStackTrace();
        }

        Product product = repository.findById((int)map.get("productId"));
        product.setCount(product.getCount() - (int)map.get("amount"));
        repository.save(product);
    }

    @KafkaListener(topics = "refund-request-topic")
    public void processRefundRequest(String kafkaMessage){
        log.info("Kafka Message: ====> " + kafkaMessage);

        Map<Object,Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try{
            map = mapper.readValue(kafkaMessage, new TypeReference<Map<Object, Object>>() {});
        }catch(JsonProcessingException e){
            e.printStackTrace();
        }

        Product product = repository.findById((int)map.get("productId"));
        product.setCount(product.getCount() + (int)map.get("amount"));
        repository.save(product);
    }

    @KafkaListener(topics = "exchange-request-topic")
    public void processExchangeRequest(String kafkaMessage){
        log.info("Kafka Message: ====> " + kafkaMessage);

        Map<Object,Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try{
            map = mapper.readValue(kafkaMessage, new TypeReference<Map<Object, Object>>() {});
        }catch(JsonProcessingException e){
            e.printStackTrace();
        }

        Product product = repository.findById((int)map.get("productId"));
        int newItemId = product.getItemId();
        String newSize = (String)map.get("size");
        String newColor = product.getColor();
        Product exchangeProduct = repository.findByItemIdAndSizeAndColor(newItemId, newSize, newColor);
        product.setCount(product.getCount() + (int)map.get("amount"));
        exchangeProduct.setCount(exchangeProduct.getCount() - (int)map.get("amount"));
        repository.save(product);
        repository.save(exchangeProduct);
    }
}
