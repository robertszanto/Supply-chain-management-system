package com.example.demo.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@ToString
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "merchant_id")
    private Merchant merchant;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    List<OrderItem> orderItems;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address deliveryAddress;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "status")
    private String orderStatus;
}
