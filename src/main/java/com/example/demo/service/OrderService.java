package com.example.demo.service;

import com.example.demo.exceptions.MerchantNotFound;
import com.example.demo.exceptions.OrderNotFoundException;
import com.example.demo.exceptions.OrderStatusException;
import com.example.demo.models.Merchant;
import com.example.demo.models.Order;
import com.example.demo.models.OrderStatus;
import com.example.demo.models.User;
import com.example.demo.respository.MerchantRepository;
import com.example.demo.respository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final MerchantRepository merchantRepository;

    @Transactional
    public Order create(Order order){

        User userForOrder = userService.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        Optional<Merchant> merchantForOrder = merchantRepository.getMerchantByName(order.getMerchant().getName());
        if (merchantForOrder.isEmpty()){
            throw new MerchantNotFound("Merchant doesn't exist");
        }
        order.setMerchant(merchantForOrder.get());
        order.setUser(userForOrder);
        order.setOrderStatus(OrderStatus.NEW.name());
        return orderRepository.save(order);
    }

    @Transactional
    public Order getById(Long id) {
        Optional<Order> orderFromDb = orderRepository.getOrderById(id);
        if (orderFromDb.isEmpty()){
            throw new OrderNotFoundException(String.format("Order with ID: %s not found id DB", id));
        }
        return orderFromDb.get();
    }

    @Transactional
    public List<Order> getUserOrders(Long userId) {
        User user = userService.getById(userId);
        Optional<List<Order>> userOrders = orderRepository.getUserOrders(user);
        if (userOrders.isEmpty()){
            throw new OrderNotFoundException(String.format("No orders where found for User: %s", user.getFirstName()));
        }
        return userOrders.get();
    }

    @Transactional
    public Order updateOrder(Order order, Long orderId) {
        Order dbOrder = getById(orderId);
        Order updatedOrder = new Order();
        updatedOrder.setOrderItems(Optional.ofNullable(order.getOrderItems()).orElse(dbOrder.getOrderItems()));
        updatedOrder.setDeliveryAddress(Optional.ofNullable(order.getDeliveryAddress()).orElse(dbOrder.getDeliveryAddress()));
        updatedOrder.setPhoneNumber(Optional.ofNullable(order.getPhoneNumber()).orElse(dbOrder.getPhoneNumber()));

        return orderRepository.save(updatedOrder);
    }

    @Transactional
    public void delete(Long id) {
        Order orderToDelete = getById(id);
        orderRepository.delete(orderToDelete);
    }

    @Transactional
    public List<Order> getAll() {
        Optional<List<Order>> allOrders = orderRepository.getAllOrders();
        if (allOrders.isEmpty()){
            throw new OrderNotFoundException("There are no orders in DB");
        }
        return allOrders.get();
    }

    @Transactional
    public List<Order> getByMerchantName(String merchantName) {
        Optional<List<Order>> ordersForMerchant = orderRepository.getOrdersByMerchantName(merchantName);
        if (ordersForMerchant.isEmpty()){
            throw new OrderNotFoundException("There are no orders in data base for this merchant: " + merchantName);
        }
        return ordersForMerchant.get();
    }

    @Transactional
    public OrderStatus updateToNextStatusState(Long orderId) {
        Order order = getById(orderId);
        OrderStatus oldStatus = OrderStatus.valueOf(order.getOrderStatus());
        if (oldStatus == OrderStatus.DELIVERED){
            throw new OrderStatusException("This is the LAST possible Order Status!!");
        }
        OrderStatus newStatus = OrderStatus.values()[oldStatus.ordinal() + 1];
        order.setOrderStatus(newStatus.toString());
        orderRepository.save(order);
        return newStatus;
    }

    @Transactional
    public OrderStatus updateToPreviousStatusState(Long orderId) {
        Order order = getById(orderId);
        OrderStatus oldStatus = OrderStatus.valueOf(order.getOrderStatus());
        if (oldStatus == OrderStatus.NEW){
            throw new OrderStatusException("This is the FIRST possible Order Status!!");
        }
        OrderStatus newStatus = OrderStatus.values()[oldStatus.ordinal() - 1];
        order.setOrderStatus(newStatus.toString());
        orderRepository.save(order);
        return newStatus;
    }

    @Transactional
    public Order cancel(Long orderId) {
        Order orderToCancel = getById(orderId);
        orderToCancel.setOrderStatus(OrderStatus.CANCELED.name());
        return orderRepository.save(orderToCancel);
    }
}
