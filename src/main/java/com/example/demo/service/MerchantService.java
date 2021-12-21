package com.example.demo.service;

import com.example.demo.models.Merchant;
import com.example.demo.respository.MerchantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MerchantService {

    private final MerchantRepository merchantRepository;

    @Transactional
    public Merchant createMerchant(Merchant merchant) {
        if (getByName(merchant.getName()).isPresent()){
            throw new RuntimeException("merchant already exists");
        }
        return merchantRepository.save(merchant);
    }

    @Transactional
    public Optional<Merchant> getByName(String name){
        return merchantRepository.getMerchantByName(name);
    }
}
