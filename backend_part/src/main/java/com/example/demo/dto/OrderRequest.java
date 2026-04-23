package com.example.demo.dto;

import java.util.List;
import java.util.Map;

public class OrderRequest {

    private Map<String, String> address;
    private List<Map<String, Object>> items;
    private Double amount;

    public Map<String, String> getAddress() {
        return address;
    }

    public void setAddress(Map<String, String> address) {
        this.address = address;
    }

    public List<Map<String, Object>> getItems() {
        return items;
    }

    public void setItems(List<Map<String, Object>> items) {
        this.items = items;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}