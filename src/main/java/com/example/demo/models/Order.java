package com.example.demo.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Order {
    private User user;
    private Customer customer;
    private int customerId;
    List<OrderItem> orderItems;
    private Address deliveryAddress;
    private String phoneNumber;
}
