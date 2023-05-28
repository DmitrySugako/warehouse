package com.sugako.service.implementations;

import com.sugako.domain.ReceiptOrder;
import com.sugako.repository.ReceiptOrderRepository;
import com.sugako.requests.ReceiptCreateRequest;
import com.sugako.requests.ReceiptUpdateRequest;
import com.sugako.service.ReceiptOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class ReceiptOrderServiceImpl implements ReceiptOrderService {
    private final ReceiptOrderRepository repository;
    private final ConversionService conversionService;

    @Transactional
    @Override
    public ReceiptOrder createReceipt(ReceiptCreateRequest receiptCreateRequest) {

        ReceiptOrder receipt = conversionService.convert(receiptCreateRequest, ReceiptOrder.class);
        receipt = repository.save(receipt);

        return receipt;
    }

    @Transactional
    @Override
    public ReceiptOrder updateReceipt(ReceiptUpdateRequest receiptUpdateRequest) {

        ReceiptOrder receipt = conversionService.convert(receiptUpdateRequest, ReceiptOrder.class);
        receipt = repository.save(receipt);

        return receipt;
    }

    @Override
    public ReceiptOrder findById(Long id) {
        return repository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Receipt with id " + id + " not found"));
    }
}
