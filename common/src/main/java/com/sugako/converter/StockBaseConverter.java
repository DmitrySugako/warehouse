package com.sugako.converter;

import com.sugako.domain.ReceiptOrder;
import com.sugako.domain.Shipment;
import com.sugako.domain.StockStatus;
import com.sugako.repository.ReceiptOrderRepository;
import com.sugako.requests.ShipmentCreateRequest;
import com.sugako.requests.StockCreateRequest;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;


public abstract class StockBaseConverter<S, T> implements Converter<S, T> {


    public StockStatus doConvert(StockStatus stockForUpdate, StockCreateRequest request) {

        stockForUpdate.setOrderedQuantity(request.getOrderedQuantity());
        stockForUpdate.setAvailableQuantity(0L);
        stockForUpdate.setReservedQuantity(0L);
        stockForUpdate.setChanged(new Timestamp(System.currentTimeMillis()));
        stockForUpdate.setIsDeleted(false);


        return stockForUpdate;
    }
}
