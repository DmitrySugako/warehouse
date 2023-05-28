package com.sugako.controller;


import com.sugako.domain.Shipment;
import com.sugako.exception.IllegalRequestException;
import com.sugako.repository.ShipmentRepository;
import com.sugako.requests.ShipmentCreateRequest;
import com.sugako.requests.ShipmentUpdateRequest;
import com.sugako.service.ShipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("rest/shipment")
@RequiredArgsConstructor
public class ShipmentRestController {

    private final ShipmentService shipmentService;

    private final ShipmentRepository shipmentRepository;

    @PostMapping
    public ResponseEntity<Shipment> createShipment(@Valid @RequestBody ShipmentCreateRequest shipmentCreateRequest,
                                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new IllegalRequestException(bindingResult);
        }

        Shipment shipment = shipmentService.createShipment(shipmentCreateRequest);
        return new ResponseEntity<>(shipment, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Shipment> updateShipment(@Valid @RequestBody ShipmentUpdateRequest shipmentUpdateRequest,
                                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new IllegalRequestException(bindingResult);
        }

        Shipment shipment = shipmentService.updateShipment(shipmentUpdateRequest);
        return new ResponseEntity<>(shipment, HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<Shipment>> getAllShipments() {
        List<Shipment> shipments = shipmentRepository.findAll();
        return new ResponseEntity<>(shipments, HttpStatus.OK);
    }

    @PutMapping("/{id}/picking/{quantity}/address/{address}")
    public ResponseEntity<Shipment> picking(@PathVariable("id") Long id,
                                            @PathVariable("quantity") Long quantity,
                                            @PathVariable("address") Long address) {
        return new ResponseEntity<>(shipmentService.picking(id, address, quantity), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Shipment> getShipmentById(@PathVariable("id") Long id) {

        Optional<Shipment> shipment = shipmentRepository.findById(id);

        return shipment.map(ResponseEntity::ok).orElseThrow(() ->
                new EntityNotFoundException("Shipment with id " + id + " not found"));
    }

    @PutMapping("/deactivate/{id}")
    public ResponseEntity<String> deactivate(@PathVariable("id") Long id) {

        Optional<Shipment> searchShipment = shipmentRepository.findById(id);

        if (searchShipment.isPresent()) {
            Shipment shipment = searchShipment.get();
            shipment.setIsDeleted(true);
            shipmentRepository.save(shipment);
            return new ResponseEntity<>("Shipment successfully deactivated", HttpStatus.OK);
        } else {
            throw new EntityNotFoundException(("Shipment with id " + id + " not found"));
        }
    }
}
