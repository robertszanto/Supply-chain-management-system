package com.example.demo.respository;

import com.example.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    @Query("select u from User u where u.id=:id")
    Optional<User> getUserById(Long id);

    @Query("select u from User u where u.email=:email")
    Optional<User> getUserByEmail(String email);

    @Query("select u from User u")
    Optional<List<User>> getAllUsers();

    @Query("select u from User u where u.firstName=:firstName")
    Optional<User> getUserByName(String firstName);

    @Query("select u from User u where u.email=:userName")
    Optional<User> getUserByUsername(String userName);
}

