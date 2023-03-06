package com.sugako;

import com.sugako.domain.Product;
import com.sugako.repository.ProductRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.sugako");

        ProductRepository productRepository = applicationContext.getBean("productRepositoryImpl", ProductRepository.class);

   //     System.out.println(productRepository.findOne(8));
        //System.out.println(productRepository.findAll());
        Product product=new Product("Misha","Sugako");
        productRepository.create(product);
     //   product.setSku("Dmitry");
      //  product.setId(26L);
     //   product.setDescription("Ivanov");
      //  productRepository.update(product);
       // productRepository.delete(26L);
        }

    }



