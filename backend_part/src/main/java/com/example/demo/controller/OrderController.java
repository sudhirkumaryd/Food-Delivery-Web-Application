package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.service.OrderService;

@RestController
@RequestMapping("/api/order")
@CrossOrigin("http://localhost:3000")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/place")
    public Map<String, Object> placeOrder(
            @RequestHeader("token") String token,
            @RequestBody Map<String, Object> orderData   // ✅ Accept body
    ) {

        String message = orderService.placeOrder(token);

        Map<String, Object> response = new HashMap<>();

        if (message.equals("Cart is empty")) {
            response.put("success", false);
            response.put("message", message);
        } else {
            response.put("success", true);
            response.put("message", message);
        }

        return response;
    }
}