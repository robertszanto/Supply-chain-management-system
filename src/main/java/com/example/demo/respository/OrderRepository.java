package com.example.demo.respository;

import com.example.demo.models.Order;
import com.example.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {


    @Query("select o from Order o where o.id = ?1")
    Optional<Order> getOrderById(Long id);

    @Query("select o from Order o where o.user = ?1")
    Optional<List<Order>> getUserOrders(User user);
}
