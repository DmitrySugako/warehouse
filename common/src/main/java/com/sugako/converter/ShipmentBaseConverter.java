package com.sugako.converter;

import com.sugako.domain.Shipment;
import com.sugako.requests.ShipmentCreateRequest;
import org.springframework.core.convert.converter.Converter;

import java.sql.Timestamp;

public abstract class ShipmentBaseConverter<S, T> implements Converter<S, T> {


    public Shipment doConvert(Shipment shipmentForUpdate, ShipmentCreateRequest request) {

        shipmentForUpdate.setShipmentNumber(request.getShipmentNumber());
        shipmentForUpdate.setShipmentStatus(false);
        shipmentForUpdate.setRemainingQuantity(request.getRemainingQuantity());
        shipmentForUpdate.setChanged(new Timestamp(System.currentTimeMillis()));
        shipmentForUpdate.setIsDeleted(false);


        return shipmentForUpdate;
    }
}
