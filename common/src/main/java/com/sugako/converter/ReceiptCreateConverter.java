package com.sugako.converter;

import com.sugako.domain.ReceiptOrder;
import com.sugako.domain.Shipment;
import com.sugako.requests.ReceiptCreateRequest;
import com.sugako.requests.ShipmentCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@RequiredArgsConstructor
public class ReceiptCreateConverter extends ReceiptBaseConverter<ReceiptCreateRequest, ReceiptOrder> {

    @Override
    public ReceiptOrder convert(ReceiptCreateRequest request) {

        ReceiptOrder receipt = new ReceiptOrder();

        receipt.setCreated(new Timestamp(System.currentTimeMillis()));

        return doConvert(receipt, request);
    }
}
