package com.sugako.repository;

import java.util.List;

public class EmptyImpl implements ProductRepository{
    @Override
    public Object findOne(Object id) {
        return null;
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public Object create(Object object) {
        return null;
    }

    @Override
    public Object update(Object object) {
        return null;
    }

    @Override
    public void delete(Object id) {

    }

    @Override
    public List<Object> searchForModifiedProducts() {

        return null;
    }

    @Override
    public void secondNewMethod() {

    }
}
