package com.sugako.requests;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;


@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Validated
public class StockCreateRequest {

    @NotNull
    @Positive
    private Long receipt;

    @NotNull
    @Positive
    private Long item;


    @NotNull
    @Positive
    private Long orderedQuantity;


    @NotNull
    @PositiveOrZero
    private Long availableQuantity;


    @NotNull
    @PositiveOrZero
    private Long reservedQuantity;
}
