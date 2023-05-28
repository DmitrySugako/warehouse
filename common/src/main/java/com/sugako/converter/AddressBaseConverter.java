package com.sugako.converter;

import com.sugako.domain.Shipment;
import com.sugako.domain.StorageAddress;
import com.sugako.requests.AddressCreateRequest;
import com.sugako.requests.ShipmentCreateRequest;
import org.springframework.core.convert.converter.Converter;

import java.sql.Timestamp;

public abstract class AddressBaseConverter<S, T> implements Converter<S, T> {


    public StorageAddress doConvert(StorageAddress addressForUpdate, AddressCreateRequest request) {

        addressForUpdate.setCellAddress(request.getCellAddress());
        addressForUpdate.setChanged(new Timestamp(System.currentTimeMillis()));
        addressForUpdate.setIsDeleted(false);


        return addressForUpdate;
    }
}

