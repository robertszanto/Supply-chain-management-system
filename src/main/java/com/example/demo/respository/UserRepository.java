package com.example.demo.respository;

import com.example.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{

    @Query("select u from User u where u.id=:id")
    Optional<User> getUserById(Long id);
}
