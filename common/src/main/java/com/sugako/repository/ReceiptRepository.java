package com.sugako.repository;

import com.sugako.domain.Product;
import com.sugako.domain.ReceiptOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ReceiptRepository extends
        JpaRepository<ReceiptOrder, Long>,
        PagingAndSortingRepository<ReceiptOrder, Long>,
        CrudRepository<ReceiptOrder, Long> {
}
