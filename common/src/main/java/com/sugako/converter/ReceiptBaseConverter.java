package com.sugako.converter;

import com.sugako.domain.Item;
import com.sugako.domain.ReceiptOrder;
import com.sugako.requests.ItemCreateRequest;
import com.sugako.requests.ReceiptCreateRequest;
import org.springframework.core.convert.converter.Converter;

import java.sql.Timestamp;

public abstract class ReceiptBaseConverter<S, T> implements Converter<S, T> {

    public ReceiptOrder doConvert(ReceiptOrder receiptForUpdate, ReceiptCreateRequest request) {

        receiptForUpdate.setReceiptNumber(request.getReceiptNumber());
        receiptForUpdate.setChanged(new Timestamp(System.currentTimeMillis()));
        receiptForUpdate.setIsDeleted(false);

        return receiptForUpdate;
    }
}