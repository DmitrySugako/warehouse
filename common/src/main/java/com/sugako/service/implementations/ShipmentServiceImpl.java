package com.sugako.service.implementations;

import com.sugako.domain.Shipment;
import com.sugako.domain.StockStatus;
import com.sugako.repository.ShipmentRepository;
import com.sugako.repository.StockStatusRepository;
import com.sugako.requests.ShipmentCreateRequest;
import com.sugako.requests.ShipmentUpdateRequest;
import com.sugako.service.ShipmentService;
import com.sugako.service.StockStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class ShipmentServiceImpl implements ShipmentService {
    private final ShipmentRepository repository;
    private final ConversionService conversionService;
    private final StockStatusRepository stockStatusRepository;
    private final StockStatusService stockService;

    @Transactional
    @Override
    public Shipment createShipment(ShipmentCreateRequest shipmentCreateRequest) {

        StockStatus stock = stockService.findById(shipmentCreateRequest.getStock());

        if (shipmentCreateRequest.getRemainingQuantity() <= stock.getAvailableQuantity()) {

            Shipment shipment = conversionService.convert(shipmentCreateRequest, Shipment.class);
            shipment.setStock(stock);
            stock.setAvailableQuantity(stock.getAvailableQuantity() - shipment.getRemainingQuantity());
            stock.setReservedQuantity(stock.getReservedQuantity() + shipment.getRemainingQuantity());

            stockStatusRepository.save(stock);
            shipment = repository.save(shipment);

            return shipment;
        } else {
            throw new IllegalArgumentException("The required amount of inventory is greater than the available");
        }

    }

    @Transactional
    @Override
    public Shipment updateShipment(ShipmentUpdateRequest shipmentUpdateRequest) {

        Shipment shipment = conversionService.convert(shipmentUpdateRequest, Shipment.class);
        shipment = repository.save(shipment);

        return shipment;
    }

    @Transactional
    @Override
    public Shipment picking(Long idShipment, Long idAddress, Long quantity) {

        Shipment shipment = repository.findById(idShipment).
                orElseThrow(() -> new EntityNotFoundException("Shipment with id " + idShipment + " not found"));

        if (idAddress == shipment.getStock().getAddress().getId() && quantity == shipment.getRemainingQuantity()) {
            shipment.setShipmentStatus(true);
            return repository.save(shipment);
        } else {
            throw new RuntimeException("Wrong address, or wrong quantity");
        }

    }
}
