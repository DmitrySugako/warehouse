package com.sugako.converter;

import com.sugako.domain.Shipment;
import com.sugako.requests.ShipmentCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@RequiredArgsConstructor
public class ShipmentCreateConverter extends ShipmentBaseConverter<ShipmentCreateRequest, Shipment> {

    @Override
    public Shipment convert(ShipmentCreateRequest request) {

        Shipment shipment = new Shipment();

        shipment.setCreated(new Timestamp(System.currentTimeMillis()));

        return doConvert(shipment, request);
    }
}
