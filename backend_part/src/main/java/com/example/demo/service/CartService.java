package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Cart;
import com.example.demo.repo.CartRepository;

import java.util.HashMap;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public void addToCart(String token, Long itemId) {

        Cart cart = cartRepository.findByUserToken(token)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUserToken(token);
                    newCart.setCartData(new HashMap<>());
                    return newCart;
                });

        cart.getCartData().put(itemId,
                cart.getCartData().getOrDefault(itemId, 0) + 1);

        cartRepository.save(cart);
    }

    public void removeFromCart(String token, Long itemId) {

        Cart cart = cartRepository.findByUserToken(token).orElse(null);
        if (cart != null && cart.getCartData().containsKey(itemId)) {

            int qty = cart.getCartData().get(itemId);

            if (qty > 1) {
                cart.getCartData().put(itemId, qty - 1);
            } else {
                cart.getCartData().remove(itemId);
            }

            cartRepository.save(cart);
        }
    }

    public Cart getCart(String token) {
        return cartRepository
                .findByUserToken(token)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUserToken(token);
                    return cartRepository.save(newCart);
                });
    }
}