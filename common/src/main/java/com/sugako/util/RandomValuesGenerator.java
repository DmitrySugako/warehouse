package com.sugako.util;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RandomValuesGenerator {

    public String uuidGenerator() {
        return UUID.randomUUID().toString();
    }
}
