package com.sugako.repository;

import com.sugako.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductAnalyticsRepository extends
        JpaRepository<Product, Long>,
        PagingAndSortingRepository<Product, Long>,
        CrudRepository<Product, Long> {
}
