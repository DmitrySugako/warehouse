package com.sugako.service;

import com.sugako.domain.Shipment;
import com.sugako.requests.ShipmentCreateRequest;
import com.sugako.requests.ShipmentUpdateRequest;

public interface ShipmentService {

    Shipment createShipment(ShipmentCreateRequest shipmentCreateRequest);

    Shipment updateShipment(ShipmentUpdateRequest shipmentUpdateRequest);

    Shipment picking(Long idShipment, Long idAddress, Long quantity);
}
