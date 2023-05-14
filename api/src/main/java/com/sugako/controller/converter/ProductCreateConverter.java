package com.sugako.controller.converter;

import com.sugako.controller.requests.ProductCreateRequest;
import com.sugako.domain.Product;

public class ProductCreateConverter extends ProductBaseConverter<ProductCreateRequest, Product> {

    @Override
    public Product convert(ProductCreateRequest request) {

        Product product= new Product();

        return doConvert(product,request);
    }
}
