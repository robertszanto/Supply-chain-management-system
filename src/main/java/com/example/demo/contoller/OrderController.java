package com.example.demo.contoller;

import com.example.demo.models.Order;
import com.example.demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getById(@PathVariable Long orderId){
        Order orderFromDataBase = orderService.getById(orderId);
        return new ResponseEntity<>(orderFromDataBase, HttpStatus.OK);
    }

    @GetMapping("/userorders/{userId}")
    public ResponseEntity< List<Order>> getUserOrders(@PathVariable Long userId){
        List<Order> userOrders = orderService.getUserOrders(userId);
        return new ResponseEntity<>(userOrders, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Order> create(@RequestBody Order order){
        Order createdOrder = orderService.create(order);
        return new ResponseEntity<>(createdOrder,HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity<Void> delete(@PathVariable Long orderId){
        orderService.delete(orderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
