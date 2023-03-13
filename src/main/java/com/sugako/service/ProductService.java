package com.sugako.service;

import com.sugako.domain.Product;

import java.util.List;

public interface ProductService {

    Product findOne(Long id);

    List<Product> findAll();

    Product create(Product object);

    Product update(Product object);

    Product delete(Long id);

     void checkingAndHardDelete();
}
