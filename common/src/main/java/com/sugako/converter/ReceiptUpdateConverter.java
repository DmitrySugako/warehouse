package com.sugako.converter;

import com.sugako.domain.ReceiptOrder;
import com.sugako.domain.Shipment;
import com.sugako.repository.ReceiptOrderRepository;
import com.sugako.repository.ShipmentRepository;
import com.sugako.requests.ReceiptUpdateRequest;
import com.sugako.requests.ShipmentUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ReceiptUpdateConverter extends ReceiptBaseConverter<ReceiptUpdateRequest, ReceiptOrder> {

    private final ReceiptOrderRepository receiptRepository;

    @Override
    public ReceiptOrder convert(ReceiptUpdateRequest source) {

        Optional<ReceiptOrder> receipt = receiptRepository.findById(source.getId());
        return doConvert(receipt.orElseThrow(EntityNotFoundException::new), source);
    }
}
