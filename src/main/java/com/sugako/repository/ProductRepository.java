package com.sugako.repository;

import com.sugako.domain.Product;

import java.util.List;

public interface ProductRepository extends CRUDRepository<Long, Product> {

    void firstNewMethod();

    void secondNewMethod();
}
