package com.example.demo.contoller;

import com.example.demo.aspect.Log;
import com.example.demo.models.Order;
import com.example.demo.models.OrderStatus;
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

    @Log
    @GetMapping("/orderid/{orderId}")
    public ResponseEntity<Order> getById(@PathVariable Long orderId){
        Order orderFromDataBase = orderService.getById(orderId);
        return new ResponseEntity<>(orderFromDataBase, HttpStatus.OK);
    }

    @Log
    @GetMapping("/userorders/{userId}")
    public ResponseEntity< List<Order>> getUserOrders(@PathVariable Long userId){
        List<Order> userOrders = orderService.getUserOrders(userId);
        return new ResponseEntity<>(userOrders, HttpStatus.OK);
    }

    @Log
    @GetMapping
    public ResponseEntity<List<Order>> getAll(){
        List<Order> orders = orderService.getAll();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @Log
    @GetMapping("/{merchantName}")
    public ResponseEntity<List<Order>> getByMerchantName(@PathVariable String merchantName){
        List<Order> orders = orderService.getByMerchantName(merchantName);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @Log
    @PostMapping
    public ResponseEntity<Order> create(@RequestBody Order order){
        Order createdOrder = orderService.create(order);
        return new ResponseEntity<>(createdOrder,HttpStatus.CREATED);
    }

    @Log
    @PatchMapping("/{orderId}")
    public ResponseEntity<Order> update(@RequestBody Order order, @PathVariable Long orderId){
        Order updatedOrder = orderService.updateOrder(order, orderId);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }

    @Log
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> delete(@PathVariable Long orderId){
        orderService.delete(orderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Log
    @PatchMapping("/status/next/{orderId}")
    public ResponseEntity<OrderStatus> updateStatus_next(@PathVariable Long orderId){
        OrderStatus newStatus = orderService.updateToNextStatusState(orderId);
        return new ResponseEntity<>(newStatus, HttpStatus.OK);
    }

    @Log
    @PatchMapping("/status/previous/{orderId}")
    public ResponseEntity<OrderStatus> updateStatus_previous(@PathVariable Long orderId){
        OrderStatus newStatus = orderService.updateToPreviousStatusState(orderId);
        return new ResponseEntity<>(newStatus, HttpStatus.OK);
    }

    @Log
    @PatchMapping("/status/{orderId}")
    public ResponseEntity<Order> cancel(@PathVariable Long orderId){
        Order canceledOrder = orderService.cancel(orderId);
        return new ResponseEntity<>(canceledOrder, HttpStatus.OK);
    }

}
