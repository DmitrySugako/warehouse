package com.sugako.controller;


import com.sugako.domain.ReceiptOrder;
import com.sugako.exception.IllegalRequestException;
import com.sugako.repository.ReceiptOrderRepository;
import com.sugako.requests.ReceiptCreateRequest;
import com.sugako.requests.ReceiptUpdateRequest;
import com.sugako.service.ReceiptOrderService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("rest/receipt")
@RequiredArgsConstructor
public class ReceiptOrderRestController {

    private final ReceiptOrderService receiptService;

    private final ReceiptOrderRepository receiptRepository;

    @PostMapping
    public ResponseEntity<ReceiptOrder> createReceipt(@Valid @RequestBody ReceiptCreateRequest receiptCreateRequest,
                                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new IllegalRequestException(bindingResult);
        }

        ReceiptOrder receipt = receiptService.createReceipt(receiptCreateRequest);
        return new ResponseEntity<>(receipt, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<ReceiptOrder> updateReceipt(@Valid @RequestBody ReceiptUpdateRequest receiptUpdateRequest,
                                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new IllegalRequestException(bindingResult);
        }

        ReceiptOrder receipt = receiptService.updateReceipt(receiptUpdateRequest);
        return new ResponseEntity<>(receipt, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ReceiptOrder>> getAllReceipts() {
        List<ReceiptOrder> receipts = receiptRepository.findAll();
        return new ResponseEntity<>(receipts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReceiptOrder> getReceiptById(@PathVariable("id") Long id) {

        Optional<ReceiptOrder> receipt = receiptRepository.findById(id);

        return receipt.map(ResponseEntity::ok).orElseThrow(() ->
                new EntityNotFoundException("Receipt with id " + id + " not found"));
    }

    @PutMapping("/deactivate/{id}")
    public ResponseEntity<String> deactivate(@PathVariable("id") Long id) {

        Optional<ReceiptOrder> searchItem = receiptRepository.findById(id);

        if (searchItem.isPresent()) {
            ReceiptOrder receipt = searchItem.get();
            receipt.setIsDeleted(true);
            receiptRepository.save(receipt);
            return new ResponseEntity<>("Receipt successfully deactivated", HttpStatus.OK);
        } else {
            throw new EntityNotFoundException(("Receipt with id " + id + " not found"));
        }
    }
}