package com.example.demo.contoller;

import com.example.demo.models.Merchant;
import com.example.demo.service.MerchantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class MerchantController {

    private final MerchantService merchantService;

    @PostMapping("/merchant")
    public ResponseEntity<Merchant> createMerchant(@RequestBody Merchant merchant){
        Merchant createdMerchant = merchantService.createMerchant(merchant);
        return new ResponseEntity<>(createdMerchant, HttpStatus.CREATED);
    }
}
