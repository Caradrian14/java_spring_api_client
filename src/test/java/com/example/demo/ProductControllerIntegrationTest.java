package com.example.demo.controller;

import com.example.demo.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCreateProduct() {
        Product product = new Product();
        product.setName("Integration Test Product");
        product.setPrice(10.0f);
        product.setAvailableStock(100);

        ResponseEntity<Product> responseEntity = restTemplate.postForEntity("/products", product, Product.class);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals("Integration Test Product", responseEntity.getBody().getName());
    }
}
