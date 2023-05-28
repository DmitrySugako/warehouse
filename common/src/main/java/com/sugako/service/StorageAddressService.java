package com.sugako.service;


import com.sugako.domain.StorageAddress;
import com.sugako.requests.AddressCreateRequest;
import com.sugako.requests.AddressUpdateRequest;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;


public interface StorageAddressService {


    StorageAddress createAddress(AddressCreateRequest addressCreateRequest);

    StorageAddress updateAddress(AddressUpdateRequest addressUpdateRequest);

    StorageAddress findById(Long id);

    @Cacheable("storage_address")
    List<StorageAddress> findAll();
}
