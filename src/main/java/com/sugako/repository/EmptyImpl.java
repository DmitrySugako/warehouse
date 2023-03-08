package com.sugako.repository;

import com.sugako.domain.Product;

import java.util.List;

public class EmptyImpl implements ProductRepository {

    @Override
    public Product findOne(Long id) {
        return null;
    }

    @Override
    public List<Product> findAll() {
        return null;
    }

    @Override
    public Product create(Product object) {
        return null;
    }

    @Override
    public Product update(Product object) {
        return null;
    }

    @Override
    public Product delete(Long id) {
        return null;
    }

    @Override
    public void firstNewMethod() {
    }

    @Override
    public void secondNewMethod() {

    }
}
