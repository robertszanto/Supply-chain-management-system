package com.example.demo.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Address address;
    private List<Authority> authorities;
}
