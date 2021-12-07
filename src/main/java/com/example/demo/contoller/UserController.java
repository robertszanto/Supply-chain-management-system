package com.example.demo.contoller;

import com.example.demo.models.User;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

//    TODO un GET in functie de email

//    TODO la save sa verifici userul se afla in baza de date in functie de email ->
//    Daca user-ul ce vrei sa il salvezi are deja emailul in baza de date sa nu il adaugi ci
//    ci sa arunci o erooare custom -> UserAlreadyInDbException();

}
