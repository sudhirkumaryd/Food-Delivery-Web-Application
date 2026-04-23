package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.CartService;
import com.example.demo.entity.Cart;

import java.util.Map;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin("http://localhost:3000")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public String addToCart(
            @RequestHeader("token") String token,
            @RequestBody Map<String, Long> body) {

        cartService.addToCart(token, body.get("itemId"));
        return "Item added to cart";
    }

    @PostMapping("/remove")
    public String removeFromCart(
            @RequestHeader("token") String token,
            @RequestBody Map<String, Long> body) {

        cartService.removeFromCart(token, body.get("itemId"));
        return "Item removed from cart";
    }

    @PostMapping("/get")
    public Cart getCart(@RequestHeader("token") String token) {
        return cartService.getCart(token);
    }
}