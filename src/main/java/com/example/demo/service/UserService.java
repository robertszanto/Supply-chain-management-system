package com.example.demo.service;

import com.example.demo.exceptions.UserAlreadyInDbException;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.models.Authority;
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
        var dbUser = userRepository.getUserByEmail(user.getEmail());
        if (dbUser.isPresent()) {
            throw new UserAlreadyInDbException("You cannot save this user to DB. User is already present in DB!");
        }
        Authority authority = new Authority();
        authority.setName("CLIENT");
        user.setAuthorities(List.of(authority));

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

    @Transactional
    public User getByEmail(String email) {
        Optional<User> result = userRepository.getUserByEmail(email);
        if(result.isEmpty()) {
            throw new UserAlreadyInDbException(String.format("This email %s has been already used", email));
        }
        return result.get();
    }

    @Transactional
    public  void  deleteUser(Long userId) {
        User currentUser = getById(userId);
        userRepository.delete(currentUser);
    }

    @Transactional
    public List<User> getAllUsers() {
        Optional<List<User>> allDbUsers = userRepository.getAllUsers();
        if (allDbUsers.isEmpty()){
            throw new UserNotFoundException("There is no Users in Data Base!");
        }
        return allDbUsers.get();
    }

    @Transactional
    public List<User> getUserByNameFirstLetter(String letter) {
        List<User> dbUsers = getAllUsers();
        List<User> filteredByFirstLetter = new ArrayList<>();
        for (User user : dbUsers) {
            if (user.getFirstName().startsWith(letter)) {
                filteredByFirstLetter.add(user);
            }
        }
        return filteredByFirstLetter;
    }
}
