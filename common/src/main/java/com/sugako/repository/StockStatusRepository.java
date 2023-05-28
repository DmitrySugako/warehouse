package com.sugako.repository;

import com.sugako.domain.Shipment;
import com.sugako.domain.StockStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockStatusRepository extends
        JpaRepository<StockStatus, Long> {


}
