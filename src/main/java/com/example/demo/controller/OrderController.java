package com.example.demo.controller;

import com.example.demo.model.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    // Lista simulada para almacenar pedidos
    private List<Order> orders = new ArrayList<>();
    private int nextId = 1;

    // Crear un nuevo pedido
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        order.setId(nextId++);
        orders.add(order);
        return ResponseEntity.ok(order);
    }

    // Obtener todos los pedidos
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orders);
    }

    // Obtener un pedido por ID
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable int id) {
        Optional<Order> order = orders.stream()
                .filter(o -> o.getId() == id)
                .findFirst();
        return order.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Actualizar un pedido
    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable int id, @RequestBody Order orderDetails) {
        Optional<Order> orderOptional = orders.stream()
                .filter(o -> o.getId() == id)
                .findFirst();

        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            order.setStatus(orderDetails.getStatus());
            order.setReference(orderDetails.getReference());
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un pedido
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable int id) {
        Optional<Order> orderOptional = orders.stream()
                .filter(o -> o.getId() == id)
                .findFirst();

        if (orderOptional.isPresent()) {
            orders.remove(orderOptional.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
