package com.example.order_service;

public class Order {
    private Long id;
    private Long userId;
    private String item;

    public Order() {
    }

    public Order(Long id, Long userId, String item) {
        this.id = id;
        this.userId = userId;
        this.item = item;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}
