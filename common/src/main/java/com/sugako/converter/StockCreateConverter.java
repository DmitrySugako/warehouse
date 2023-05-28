package com.sugako.converter;

import com.sugako.domain.Shipment;
import com.sugako.domain.StockStatus;
import com.sugako.requests.ShipmentCreateRequest;
import com.sugako.requests.StockCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@RequiredArgsConstructor
public class StockCreateConverter extends StockBaseConverter<StockCreateRequest, StockStatus> {

    @Override
    public StockStatus convert(StockCreateRequest request) {

        StockStatus stock = new StockStatus();

        stock.setCreated(new Timestamp(System.currentTimeMillis()));

        return doConvert(stock, request);
    }
}
