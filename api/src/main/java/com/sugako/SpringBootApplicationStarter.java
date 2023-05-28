package com.sugako;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(scanBasePackages = "com.sugako")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableWebMvc
@EnableTransactionManagement
@EnableCaching


public class SpringBootApplicationStarter {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplicationStarter.class, args);

    }
}
