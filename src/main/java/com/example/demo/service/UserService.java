package com.example.demo.service;

import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.models.User;
import com.example.demo.respository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public User getById(Long id){
        Optional<User> result = userRepository.getUserById(id);
        if (result.isEmpty()){
            throw new UserNotFoundException(String.format("User with id %s not found", id));
        }
        return result.get();
    }
}
