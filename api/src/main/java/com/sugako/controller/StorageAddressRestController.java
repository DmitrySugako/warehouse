package com.sugako.controller;

import com.sugako.domain.StorageAddress;
import com.sugako.exception.IllegalRequestException;
import com.sugako.repository.StorageAddressRepository;
import com.sugako.requests.AddressCreateRequest;
import com.sugako.requests.AddressUpdateRequest;
import com.sugako.service.StorageAddressService;
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
@RequestMapping("rest/address")
@RequiredArgsConstructor
public class StorageAddressRestController {

    private final StorageAddressService storageAddressService;

    private final StorageAddressRepository storageAddressRepository;

    @PostMapping
    public ResponseEntity<StorageAddress> createAddress(@Valid @RequestBody AddressCreateRequest addressCreateRequest,
                                                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new IllegalRequestException(bindingResult);
        }

        StorageAddress address = storageAddressService.createAddress(addressCreateRequest);
        return new ResponseEntity<>(address, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<StorageAddress> updateAddress(@Valid @RequestBody AddressUpdateRequest addressUpdateRequest,
                                                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new IllegalRequestException(bindingResult);
        }

        StorageAddress address = storageAddressService.updateAddress(addressUpdateRequest);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<StorageAddress>> getAllAddresses() {
        List<StorageAddress> addresses = storageAddressService.findAll();
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StorageAddress> getAddressById(@PathVariable("id") Long id) {

        Optional<StorageAddress> address = storageAddressRepository.findById(id);

        return address.map(ResponseEntity::ok).orElseThrow(() ->
                new EntityNotFoundException("Address with id " + id + " not found"));
    }

    @PutMapping("/deactivate/{id}")
    public ResponseEntity<String> deactivate(@PathVariable("id") Long id) {

        Optional<StorageAddress> searchAddress = storageAddressRepository.findById(id);

        if (searchAddress.isPresent()) {
            StorageAddress address = searchAddress.get();
            address.setIsDeleted(true);
            storageAddressRepository.save(address);
            return new ResponseEntity<>("Address successfully deactivated", HttpStatus.OK);
        } else {
            throw new EntityNotFoundException(("Address with id " + id + " not found"));
        }
    }
}
