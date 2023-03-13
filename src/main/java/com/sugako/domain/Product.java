package com.sugako.domain;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
    private String srp1;
    private String srp2;
    private String srp3;
    private Long barcode;
    private Double weight;
    private Timestamp created;
    private Timestamp changed;
    private Boolean isDeleted;

    public Product (String sku, String description, String srp1, String srp2, String srp3, Long barcode, Double weight) {
        this.sku = sku;
        this.description = description;
        this.srp1 = srp1;
        this.srp2 = srp2;
        this.srp3 = srp3;
        this.barcode = barcode;
        this.weight = weight;

    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
