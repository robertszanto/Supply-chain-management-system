package com.example.demo.respository;

import com.example.demo.models.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Long> {

    @Query("select m from Merchant m where m.name = ?1")
    Optional<Merchant> getMerchantByName(String name);
}
