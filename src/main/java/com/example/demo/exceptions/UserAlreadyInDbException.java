package com.example.demo.exceptions;

public class UserAlreadyInDbException extends RuntimeException {
    public UserAlreadyInDbException(String maessage) {
        super(maessage);
    }
}
