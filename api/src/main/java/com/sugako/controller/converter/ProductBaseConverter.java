package com.sugako.controller.converter;

import com.sugako.controller.requests.ProductCreateRequest;
import com.sugako.domain.Product;
import org.springframework.core.convert.converter.Converter;

import java.sql.Timestamp;

public abstract class ProductBaseConverter<S, T> implements Converter<S,T> {

    public Product doConvert(Product productForUpdate, ProductCreateRequest request) {

        productForUpdate.setSku(request.getSku());
        productForUpdate.setDescription(request.getDescription());

        productForUpdate.setSrp1(request.getSrp1());
        productForUpdate.setSrp2(request.getSrp2());
        productForUpdate.setSrp3(request.getSrp3());
        productForUpdate.setBarcode(request.getBarcode());
        productForUpdate.setWeight(request.getWeight());
        productForUpdate.setCreated(request.getCreated());
        productForUpdate.setChanged(request.getChanged());
        productForUpdate.setIsDeleted(request.getIsDeleted());

        return productForUpdate;
    }
}
