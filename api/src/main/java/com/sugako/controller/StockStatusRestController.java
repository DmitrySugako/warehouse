package com.sugako.controller;


import com.sugako.domain.StockStatus;
import com.sugako.exception.IllegalRequestException;
import com.sugako.repository.StockStatusRepository;
import com.sugako.requests.StockCreateRequest;
import com.sugako.requests.StockUpdateRequest;
import com.sugako.service.StockStatusService;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("rest/stock")
@RequiredArgsConstructor
public class StockStatusRestController {

    private final StockStatusService stockStatusService;

    private final StockStatusRepository stockStatusRepository;

    @Operation(
            summary = "Adding new stock",
            description = "Adding stock based on a receipt and an item in it",
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
    public ResponseEntity<StockStatus> createStock(@Valid @RequestBody StockCreateRequest stockCreateRequest,
                                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new IllegalRequestException(bindingResult);
        }

        StockStatus stock = stockStatusService.createStock(stockCreateRequest);
        return new ResponseEntity<>(stock, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Update stock",
            description = "Change in existing stock",
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
    public ResponseEntity<StockStatus> updateStock(@Valid @RequestBody StockUpdateRequest stockUpdateRequest,
                                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new IllegalRequestException(bindingResult);
        }

        StockStatus stock = stockStatusService.updateStock(stockUpdateRequest);
        return new ResponseEntity<>(stock, HttpStatus.OK);
    }

    @Operation(
            summary = "Search all stocks",
            description = "Find all stocks without limits",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded stocks",
                            content = @Content(mediaType = "application/json", schema =
                            @Schema(implementation = StockStatus.class))
                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<StockStatus>> getAllStock() {
        List<StockStatus> stock = stockStatusRepository.findAll();
        return new ResponseEntity<>(stock, HttpStatus.OK);
    }

    @Operation(
            summary = "Search by id number",
            description = "Search for a specific stock",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded stock",
                            content = @Content(mediaType = "application/json", schema =
                            @Schema(implementation = StockStatus.class))
                    ), @ApiResponse(
                    responseCode = "NOT_FOUND", description = "Stock not found"
            )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<StockStatus> getStockById(@PathVariable("id") Long id) {

        Optional<StockStatus> stock = stockStatusRepository.findById(id);

        return stock.map(ResponseEntity::ok).orElseThrow(() ->
                new EntityNotFoundException("Stock with id " + id + " not found"));
    }

    @Operation(
            summary = "Putting stock into warehouse",
            description = "The approval, by means of ID confirmation of supply," +
                    " its quantity and selection of the cell address storage",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded stock",
                            content = @Content(mediaType = "application/json", schema =
                            @Schema(implementation = StockStatus.class))
                    )
            }
    )
    @PutMapping("/{id}/acceptance/{quantity}/address/{address}")
    public ResponseEntity<StockStatus> itemAcceptance(@PathVariable("id") Long id,
                                                      @PathVariable("quantity") Long quantity,
                                                      @PathVariable("address") Long address) {

        return new ResponseEntity<>(stockStatusService.acceptance(id, address, quantity), HttpStatus.OK);
    }

    @Operation(
            summary = "Soft delete",
            description = "Deactivates the selected stock",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Stock successfully deactivated",
                            content = @Content(mediaType = "application/json", schema =
                            @Schema(implementation = StockStatus.class))
                    )
            }
    )
    @PutMapping("/deactivate/{id}")
    public ResponseEntity<String> deactivate(@PathVariable("id") Long id) {

        Optional<StockStatus> searchStock = stockStatusRepository.findById(id);

        if (searchStock.isPresent()) {
            StockStatus stock = searchStock.get();
            stock.setIsDeleted(true);
            stockStatusRepository.save(stock);
            return new ResponseEntity<>("Stock successfully deactivated", HttpStatus.OK);
        } else {
            throw new EntityNotFoundException(("Stock with id " + id + " not found"));
        }
    }


}
