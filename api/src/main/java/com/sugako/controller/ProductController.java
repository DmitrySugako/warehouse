package com.sugako.controller;


import com.sugako.controller.requests.ProductUpdateRequest;
import com.sugako.domain.Product;
import com.sugako.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("rest/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;

    private final ConversionService conversionService;

    @GetMapping
    public ResponseEntity<Object> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> findProduct(@PathVariable Long id){
        Optional<Product> product = productRepository.findById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Object> updateUser(@Valid @RequestBody ProductUpdateRequest request) {

        Product product = conversionService.convert(request, Product.class);
        product = productRepository.save(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

}
