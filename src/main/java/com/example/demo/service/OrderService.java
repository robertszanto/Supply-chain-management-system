package com.example.demo.service;

import com.example.demo.models.Order;
import com.example.demo.models.User;
import com.example.demo.respository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;

    @Transactional
    public Order create(Order order){
        User userForOrder = userService.getById(11L);
        order.setUser(userForOrder);
        return orderRepository.save(order);
    }

    @Transactional
    public void delete(Long id) {
        Order orderToDelete = getById(id);
        orderRepository.delete(orderToDelete);
    }

    @Transactional
    public Order getById(Long id) {
        Optional<Order> orderFromDb = orderRepository.getOrderById(id);
        if (orderFromDb.isEmpty()){
            throw new RuntimeException("blabla");
        }
        return orderFromDb.get();
    }

    @Transactional
    public List<Order> getUserOrders(Long userId) {
        User user = userService.getById(userId);
        Optional<List<Order>> userOrders = orderRepository.getUserOrders(user);
        if (userOrders.isEmpty()){
            throw new RuntimeException("blblablablaba");
        }
        return userOrders.get();
    }
}
