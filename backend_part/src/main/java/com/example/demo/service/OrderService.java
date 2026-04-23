package com.example.demo.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Cart;
import com.example.demo.entity.Order;
import com.example.demo.repo.CartRepository;
import com.example.demo.repo.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;

    public String placeOrder(String token) {

        // 1️⃣ Find Cart
    	Optional<Cart> optionalCart = cartRepository.findByUserToken(token);

    	if (optionalCart.isEmpty()) {
    	    throw new RuntimeException("Cart is empty");
    	}

    	Cart cart = optionalCart.get();
        if (cart == null || cart.getCartData().isEmpty()) {
            return "Cart is empty";
        }

        // 2️⃣ Create Order
        Order order = new Order();
        order.setUserToken(token);

        // 3️⃣ Convert cart items to JSON string
        String itemsJson = cart.getCartData().toString();
        order.setItemsJson(itemsJson);

        // 4️⃣ Calculate total amount (temporary logic)
        double total = 0;
        for (Integer qty : cart.getCartData().values()) {
            total += qty * 10;   // temporary price logic
        }

        order.setAmount(total);

        // 5️⃣ Save Order
        orderRepository.save(order);

        // 6️⃣ Clear Cart
        cart.getCartData().clear();
        cartRepository.save(cart);

        return "Order placed successfully";
    }
}