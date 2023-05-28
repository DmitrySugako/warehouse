package com.sugako.repository;

import com.sugako.domain.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ShipmentRepository extends
        JpaRepository<Shipment, Long> {


}
