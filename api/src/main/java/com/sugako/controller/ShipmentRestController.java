package com.sugako.controller;


import com.sugako.domain.Shipment;
import com.sugako.domain.StockStatus;
import com.sugako.exception.IllegalRequestException;
import com.sugako.repository.ShipmentRepository;
import com.sugako.requests.ShipmentCreateRequest;
import com.sugako.requests.ShipmentUpdateRequest;
import com.sugako.service.ShipmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(
            summary = "Adding new shipment",
            description = "Adding stock to a shipping order. Added amount is reserved stock status",
            responses = {
                    @ApiResponse(
                            responseCode = "CREATED",
                            description = "Stock added successfully",
                            content = @Content(mediaType = "application/json", schema =
                            @Schema(implementation = StockStatus.class))
                    )
            }
    )
    @PostMapping
    public ResponseEntity<Shipment> createShipment(@Valid @RequestBody ShipmentCreateRequest shipmentCreateRequest,
                                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new IllegalRequestException(bindingResult);
        }

        Shipment shipment = shipmentService.createShipment(shipmentCreateRequest);
        return new ResponseEntity<>(shipment, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Update shipment",
            description = "Change in existing shipment",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Changes were successful",
                            content = @Content(mediaType = "application/json", schema =
                            @Schema(implementation = StockStatus.class))
                    )
            }
    )
    @PutMapping()
    public ResponseEntity<Shipment> updateShipment(@Valid @RequestBody ShipmentUpdateRequest shipmentUpdateRequest,
                                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new IllegalRequestException(bindingResult);
        }

        Shipment shipment = shipmentService.updateShipment(shipmentUpdateRequest);
        return new ResponseEntity<>(shipment, HttpStatus.OK);
    }

    @Operation(
            summary = "Search all shipments",
            description = "Find all shipments without limits",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded shipments",
                            content = @Content(mediaType = "application/json", schema =
                            @Schema(implementation = StockStatus.class))
                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<Shipment>> getAllShipments() {
        List<Shipment> shipments = shipmentRepository.findAll();
        return new ResponseEntity<>(shipments, HttpStatus.OK);
    }

    @Operation(
            summary = "Stock assembly",
            description = "Stock assembly, with address and quantity confirmation",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded shipment",
                            content = @Content(mediaType = "application/json", schema =
                            @Schema(implementation = StockStatus.class))
                    )
            }
    )
    @PutMapping("/{id}/pick/q={quantity}/a={address}")
    public ResponseEntity<Shipment> picking(@PathVariable("id") @Parameter(description = "shipment id number") Long id,
                                            @PathVariable("quantity") @Parameter(description = "quantity") Long quantity,
                                            @PathVariable("address") @Parameter(description = "address id number") Long address) {
        return new ResponseEntity<>(shipmentService.picking(id, address, quantity), HttpStatus.OK);
    }

    @Operation(
            summary = "Search by id number",
            description = "Search for a specific shipment",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded shipment",
                            content = @Content(mediaType = "application/json", schema =
                            @Schema(implementation = StockStatus.class))
                    ),
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Shipment> getShipmentById(@PathVariable("id") Long id) {

        Optional<Shipment> shipment = shipmentRepository.findById(id);

        return shipment.map(ResponseEntity::ok).orElseThrow(() ->
                new EntityNotFoundException("Shipment with id " + id + " not found"));
    }

    @Operation(
            summary = "Soft delete",
            description = "Deactivates the selected shipment",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Shipment successfully deactivated",
                            content = @Content(mediaType = "application/json", schema =
                            @Schema(implementation = StockStatus.class))
                    )
            }
    )
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
