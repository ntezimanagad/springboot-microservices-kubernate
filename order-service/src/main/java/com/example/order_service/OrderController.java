package com.example.order_service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class OrderController {
    private final RestTemplate rest;

    public OrderController(RestTemplate rest) {
        this.rest = rest;
    }

    @GetMapping("/orders")
    public List<Map<String, Object>> orders() {
        // sample orders
        List<Order> orders = List.of(
                new Order(100L, 1L, "keyboard"),
                new Order(101L, 2L, "mouse"),
                new Order(102L, 1L, "monitor"));

        // call user-service via kubernetes DNS name (service name = user-service)
        UserDTO[] users = rest.getForObject("http://user-service:8080/users", UserDTO[].class);

        // map userId -> user
        var userMap = users == null ? Map.of()
                : java.util.Arrays.stream(users)
                        .collect(Collectors.toMap(UserDTO::getId, u -> u));

        // attach user info
        return orders.stream()
                .map(o -> Map.of(
                        "orderId", o.getId(),
                        "item", o.getItem(),
                        "user", userMap.get(o.getUserId())))
                .collect(Collectors.toList());
    }
}
