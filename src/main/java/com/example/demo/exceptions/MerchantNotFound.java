package com.example.demo.exceptions;

public class MerchantNotFound extends RuntimeException{
    public MerchantNotFound(String message) {
        super(message);
    }
}
