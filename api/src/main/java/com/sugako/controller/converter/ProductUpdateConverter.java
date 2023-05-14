package com.sugako.controller.converter;

import com.sugako.controller.requests.ProductUpdateRequest;
import com.sugako.domain.Product;
import com.sugako.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductUpdateConverter extends ProductBaseConverter<ProductUpdateRequest, Product>{

    private final ProductRepository repository;

    @Override
    public Product convert(ProductUpdateRequest source) {

        Optional<Product> product = repository.findById(source.getId());
        return doConvert(product.orElseThrow(EntityNotFoundException::new), source);
    }
}
