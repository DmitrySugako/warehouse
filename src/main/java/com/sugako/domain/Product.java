package com.sugako.domain;


import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.sql.Timestamp;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class Product {

    private Long id;
    private String sku;
    private String description;
    private Timestamp created;
    private Timestamp changed;
    private Boolean isDeleted;

    public Product(String sku, String description){
        this.sku=sku;
        this.description=description;


    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
