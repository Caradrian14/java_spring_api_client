package com.example.demo;

import com.example.demo.model.Product;
import com.example.demo.model.Order;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderControllerIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCreateOrder() {
        Order order = new Order();
        order.setStatus("available");
        order.setReference("Integration Test Order");
        ResponseEntity<Order> responseEntity = restTemplate.postForEntity("/orders", order, Order.class);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals("Integration Test Order", responseEntity.getBody().getReference());
    }
}
