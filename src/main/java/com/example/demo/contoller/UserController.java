package com.example.demo.contoller;

import com.example.demo.models.User;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor   //constructor prin lombok (dependency injection prin constructor)
public class UserController {

    private final UserService userService; // injectare clasa

    @PostMapping("/user")    //endpoint
    public ResponseEntity<User> save(@RequestBody User user) {
        User userAdded = userService.saveUser(user);
        return new ResponseEntity<>(userAdded, HttpStatus.CREATED);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id){
        User result = userService.getById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<User> getByEmail(@PathVariable String email) {
        User result = userService.getByEmail(email);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/user/{userId}")
    ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<List<User>> getAll() {
        List<User> result = userService.getAllUsers();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/user/firstname/{letter}")
    public ResponseEntity<List<User>> getByName(@PathVariable String letter){
        List<User> result = userService.getUserByNameFirstLetter(letter);
        System.out.println(result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }




}
