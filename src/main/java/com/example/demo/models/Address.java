package com.example.demo.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.aspectj.lang.annotation.control.CodeGenerationHint;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "address")
@ToString
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String country;

    private String city;

    private String street;

    @Column(name = "house_number")
    private int number;

    @Column(name = "zip_code")
    private int zipCode;
}
