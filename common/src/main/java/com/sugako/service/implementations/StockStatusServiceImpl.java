package com.sugako.service.implementations;

import com.sugako.domain.Item;
import com.sugako.domain.ReceiptOrder;
import com.sugako.domain.StockStatus;
import com.sugako.domain.StorageAddress;
import com.sugako.repository.StockStatusRepository;
import com.sugako.requests.StockCreateRequest;
import com.sugako.requests.StockUpdateRequest;
import com.sugako.service.StockStatusService;
import com.sugako.service.StorageAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityNotFoundException;



@Service
@RequiredArgsConstructor
public class StockStatusServiceImpl implements StockStatusService {
    private final StockStatusRepository repository;
    private final ReceiptOrderServiceImpl receiptOrderService;
    private final ItemServiceImpl itemService;

    private final StorageAddressService addressService;
    private final ConversionService conversionService;

    @Transactional
    @Override
    public StockStatus createStock(StockCreateRequest stockCreateRequest) {

        StockStatus stock = conversionService.convert(stockCreateRequest, StockStatus.class);

        ReceiptOrder receipt = receiptOrderService.findById(stockCreateRequest.getReceipt());
        stock.setReceipt(receipt);

        Item item = itemService.findById(stockCreateRequest.getItem());
        stock.setItem(item);

        stock = repository.save(stock);

        return stock;
    }

    @Transactional
    @Override
    public StockStatus updateStock(StockUpdateRequest stockUpdateRequest) {

        StockStatus stock = conversionService.convert(stockUpdateRequest, StockStatus.class);
        stock = repository.save(stock);

        return stock;
    }

    @Override
    public StockStatus findById(Long id) {
        return repository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Receipt with id " + id + " not found"));
    }


    @Transactional
    @Override
    public StockStatus acceptance(Long idStock, Long idAddress, Long quantity) {
        StockStatus stock = repository.findById(idStock).
                orElseThrow(() -> new EntityNotFoundException("Stock with id " + idStock + " not found"));

        if (quantity <= stock.getOrderedQuantity()) {

            stock.setAvailableQuantity(quantity);
            stock.setOrderedQuantity(stock.getOrderedQuantity() - quantity);

            StorageAddress address = addressService.findById(idAddress);
            stock.setAddress(address);
            return repository.save(stock);
        } else {
            throw new IllegalArgumentException("Quantity more than ordered");
        }

    }

}

