package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userToken;

    @ElementCollection
    @CollectionTable(name = "cart_items")
    @MapKeyColumn(name = "item_id")
    @Column(name = "quantity")
    private Map<Long, Integer> cartData = new HashMap<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}

	public Map<Long, Integer> getCartData() {
		return cartData;
	}

	public void setCartData(Map<Long, Integer> cartData) {
		this.cartData = cartData;
	}

    
}