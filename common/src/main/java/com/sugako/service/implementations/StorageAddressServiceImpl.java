package com.sugako.service.implementations;


import com.sugako.domain.StorageAddress;
import com.sugako.repository.StorageAddressRepository;
import com.sugako.requests.AddressCreateRequest;
import com.sugako.requests.AddressUpdateRequest;
import com.sugako.service.StorageAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;


@Service
@RequiredArgsConstructor
public class StorageAddressServiceImpl implements StorageAddressService {
    private final StorageAddressRepository repository;
    private final ConversionService conversionService;

    @Transactional
    @Override
    public StorageAddress createAddress(AddressCreateRequest addressCreateRequest) {

        StorageAddress address = conversionService.convert(addressCreateRequest, StorageAddress.class);
        address = repository.save(address);

        return address;
    }

    @Transactional
    @Override
    public StorageAddress updateAddress(AddressUpdateRequest stockUpdateRequest) {

        StorageAddress address = conversionService.convert(stockUpdateRequest, StorageAddress.class);
        address = repository.save(address);

        return address;
    }

    public StorageAddress findById(Long id) {
        return repository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Address with id " + id + " not found"));
    }

    @Override
    public List<StorageAddress> findAll() {
        return repository.findAll();
    }
}
