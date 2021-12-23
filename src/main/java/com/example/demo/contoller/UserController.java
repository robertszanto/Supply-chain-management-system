package com.example.demo.contoller;

import com.example.demo.aspect.Log;
import com.example.demo.models.User;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Log
    @PostMapping("/user/client")
    public ResponseEntity<User> save_client(@RequestBody User user) {
        User userAdded = userService.saveClientUser(user);
        return new ResponseEntity<>(userAdded, HttpStatus.CREATED);
    }

    @Log
    @PostMapping("/user/merchant")
    public ResponseEntity<User> save_merchant(@RequestBody User user) {
        User userAdded = userService.saveMerchantUser(user);
        return new ResponseEntity<>(userAdded, HttpStatus.CREATED);
    }
    @Log
    @PostMapping("/user/admin")
    public ResponseEntity<User> save_admin(@RequestBody User user) {
        User userAdded = userService.saveAdminUser(user);
        return new ResponseEntity<>(userAdded, HttpStatus.CREATED);
    }

    @Log
    @GetMapping("/user/userid/{userId}")
    public ResponseEntity<User> getById(@PathVariable Long userId){
        User result = userService.getById(userId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Log
    @GetMapping("/user/email/{email}")
    public ResponseEntity<User> getByEmail(@PathVariable String email) {
        User result = userService.getByEmail(email);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Log
    @DeleteMapping("/user/{userId}")
    ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
