package com.sugako.repository;

import com.sugako.domain.Product;
import com.sugako.domain.ProductAnalytics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductAnalyticsRepository extends
        JpaRepository<ProductAnalytics, Long>,
        PagingAndSortingRepository<ProductAnalytics, Long>,
        CrudRepository<ProductAnalytics, Long> {
}
