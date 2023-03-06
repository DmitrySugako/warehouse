package com.sugako.repository;

import java.util.List;

public interface ProductRepository extends CRUDRepository {

    List<Object> searchForModifiedProducts();

    void secondNewMethod();
}
