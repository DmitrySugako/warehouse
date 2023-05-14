package com.sugako.controller;


import com.sugako.domain.Product;
import com.sugako.domain.ReceiptOrder;
import com.sugako.repository.ProductRepository;
import com.sugako.repository.ReceiptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("rest/receipt")
@RequiredArgsConstructor


public class ReceiptController {

    private final ReceiptRepository receipt;

    @GetMapping
    public ResponseEntity<Object> getAllReceipts() {
        List<ReceiptOrder> receipts = receipt.findAll();
        return new ResponseEntity<>(receipts, HttpStatus.OK);
    }
}
