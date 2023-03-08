package com.sugako.service;

import com.sugako.domain.Product;
import com.sugako.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product findOne(Long id) {
        return productRepository.findOne(id);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product create(Product object) {
        return productRepository.create(object);
    }

    @Override
    public Product update(Product object) {
        return productRepository.update(object);
    }

    @Override
    public Product delete(Long id) {
        return productRepository.delete(id);

    }
}
