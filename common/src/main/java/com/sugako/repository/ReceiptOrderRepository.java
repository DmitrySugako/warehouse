package com.sugako.repository;

import com.sugako.domain.ReceiptOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiptOrderRepository extends
        JpaRepository<ReceiptOrder, Long> {
}
