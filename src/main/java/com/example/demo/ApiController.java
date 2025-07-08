package com.example.demo;

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

}
