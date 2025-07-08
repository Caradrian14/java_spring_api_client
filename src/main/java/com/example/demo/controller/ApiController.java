package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.model.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, World!";
    }

    @PostMapping("/user")
    public String createUser(@RequestBody User user) {
        return "User created: " + user.getName() + ", " + user.getEmail();
    }

    @PostMapping("/product")
    public String createProduct(@RequestBody Product product) {
        return "Product created: " + product.getName() + ", with stock available: " + product.getAvailableStock();
    }

}
