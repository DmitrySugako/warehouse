package com.sugako;

import com.sugako.domain.Product;
import com.sugako.domain.User;
import com.sugako.repository.ProductRepository;
import com.sugako.repository.UserRepository;
import com.sugako.service.ProductService;
import com.sugako.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.sugako");

      //  ProductRepository productRepository = applicationContext.getBean("productRepositoryImpl", ProductRepository.class);
      //  ProductService productService = applicationContext.getBean("productServiceImpl", ProductService.class);

      //  Product testProduct = new Product("NB2323", "good laptop", "mobile devices", "laptops", "hpi", 1365489654136L, 5.1);

     //   System.out.println(productService.findAll());
    //   System.out.println(productService.create(testProduct));
     //   testProduct.setSku("NB232355");
     //   System.out.println(productService.update(testProduct));
      //  System.out.println(productService.findOne(34L));
      //  System.out.println(productService.delete(34L));

      //  productService.checkingAndHardDelete();
        UserService userService=applicationContext.getBean("userServiceImpl",UserService.class);
        UserRepository userRepository=applicationContext.getBean("userRepositoryImpl",UserRepository.class);
      //  User user=new User("Dmitry","Sugako","sddd","12d34",3L);
      //  System.out.println(userService.create(user));
        System.out.println(userService.findAll());

    }

}



