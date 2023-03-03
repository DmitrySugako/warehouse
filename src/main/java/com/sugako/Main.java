package com.sugako;

import com.sugako.repository.ProductRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.sugako");

        ProductRepository productRepository = applicationContext.getBean("productRepositoryImpl", ProductRepository.class);

        System.out.println(productRepository.findOne(2));
        //System.out.println(productRepository.findAll());

        }

    }



