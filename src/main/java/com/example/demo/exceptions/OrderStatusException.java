package com.example.demo.exceptions;

public class OrderStatusException extends RuntimeException{
    public OrderStatusException(String message) {
        super(message);
    }
}
