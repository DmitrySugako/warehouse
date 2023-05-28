package com.sugako.converter;

import com.sugako.domain.Shipment;
import com.sugako.repository.ShipmentRepository;
import com.sugako.requests.ShipmentUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ShipmentUpdateConverter extends ShipmentBaseConverter<ShipmentUpdateRequest, Shipment> {

    private final ShipmentRepository shipmentRepository;

    @Override
    public Shipment convert(ShipmentUpdateRequest source) {

        Optional<Shipment> shipment = shipmentRepository.findById(source.getId());
        return doConvert(shipment.orElseThrow(EntityNotFoundException::new), source);
    }
}
