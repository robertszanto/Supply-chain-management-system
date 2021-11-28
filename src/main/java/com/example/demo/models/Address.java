package com.example.demo.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class Address {
    private String country;
    private String city;
    private String street;
    private String number;
    private String zipCode;
}
