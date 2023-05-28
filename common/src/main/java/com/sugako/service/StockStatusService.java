package com.sugako.service;


import com.sugako.domain.StockStatus;
import com.sugako.requests.StockCreateRequest;
import com.sugako.requests.StockUpdateRequest;



public interface StockStatusService {

    StockStatus createStock(StockCreateRequest stockCreateRequest);

    StockStatus updateStock(StockUpdateRequest stockUpdateRequest);

    StockStatus findById(Long id);

    StockStatus acceptance(Long idStock, Long idAddress, Long quantity);
}
