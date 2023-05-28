package com.sugako.service;



import com.sugako.domain.ReceiptOrder;
import com.sugako.requests.ReceiptCreateRequest;
import com.sugako.requests.ReceiptUpdateRequest;

public interface ReceiptOrderService {

    ReceiptOrder createReceipt(ReceiptCreateRequest receiptCreateRequest);

    ReceiptOrder updateReceipt(ReceiptUpdateRequest receiptUpdateRequest);

    ReceiptOrder findById(Long id);
}
