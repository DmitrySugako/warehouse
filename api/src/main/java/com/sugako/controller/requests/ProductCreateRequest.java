package com.sugako.controller.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Validated
public class ProductCreateRequest {

    @Size(min = 3, max = 20)
    @NotNull
    private String sku;

    @Size(min=3, max = 100)
    @NotNull
    private String description;

    @Size(min = 3, max=6)
    @NotNull
    private String srp1;

    @Size(min = 3, max=6)
    @NotNull
    private String srp2;

    @Size(min = 3, max=6)
    @NotNull
    private String srp3;

    @Size(min = 6, max=14)
    @NotNull
    private Long barcode;

    @Size(min = 1, max=4)
    @NotNull
    @Positive
    private Double weight;

    @NotNull
    private Timestamp created;

    @NotNull
    private Timestamp changed;

    @NotNull
    private Boolean isDeleted;
}
