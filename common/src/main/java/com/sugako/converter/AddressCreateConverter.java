package com.sugako.converter;


import com.sugako.domain.StorageAddress;
import com.sugako.requests.AddressCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@RequiredArgsConstructor
public class AddressCreateConverter extends AddressBaseConverter<AddressCreateRequest, StorageAddress> {

    @Override
    public StorageAddress convert(AddressCreateRequest request) {

        StorageAddress address = new StorageAddress();

        address.setCreated(new Timestamp(System.currentTimeMillis()));

        return doConvert(address, request);
    }
}
