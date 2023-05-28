package com.sugako.converter;

import com.sugako.domain.Shipment;
import com.sugako.domain.StockStatus;
import com.sugako.repository.ShipmentRepository;
import com.sugako.repository.StockStatusRepository;
import com.sugako.requests.ShipmentUpdateRequest;
import com.sugako.requests.StockUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class StockUpdateConverter extends StockBaseConverter<StockUpdateRequest, StockStatus> {

    private final StockStatusRepository stockRepository;

    @Override
    public StockStatus convert(StockUpdateRequest source) {

        Optional<StockStatus> stock = stockRepository.findById(source.getId());
        return doConvert(stock.orElseThrow(EntityNotFoundException::new), source);
    }
}
