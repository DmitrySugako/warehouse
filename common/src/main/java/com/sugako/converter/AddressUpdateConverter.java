package com.sugako.converter;

import com.sugako.domain.Shipment;
import com.sugako.domain.StorageAddress;
import com.sugako.repository.ShipmentRepository;
import com.sugako.repository.StorageAddressRepository;
import com.sugako.requests.AddressUpdateRequest;
import com.sugako.requests.ShipmentUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AddressUpdateConverter extends AddressBaseConverter<AddressUpdateRequest, StorageAddress> {

    private final StorageAddressRepository addressRepository;

    @Override
    public StorageAddress convert(AddressUpdateRequest source) {

        Optional<StorageAddress> address = addressRepository.findById(source.getId());
        return doConvert(address.orElseThrow(EntityNotFoundException::new), source);
    }
}
