package com.sugako.configuration;

import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Getter
@Setter
@Configuration
public class CacheConfiguration {

    private Integer initialCapacity;

    private Long maximumSize;

    private Long expireAfterAccessDays;

    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager("storage_address");
        cacheManager.setCaffeine(cacheProperties());
        return cacheManager;
    }

    public Caffeine<Object, Object> cacheProperties() {
        return Caffeine.newBuilder()
                .initialCapacity(30)
                .maximumSize(500)
                .expireAfterAccess(1, TimeUnit.DAYS)
                .weakKeys()
                .recordStats();
    }
}
