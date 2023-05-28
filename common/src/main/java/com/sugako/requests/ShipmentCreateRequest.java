package com.sugako.requests;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Validated
public class ShipmentCreateRequest {


    @NotNull
    @Positive
    private Long shipmentNumber;

    @NotNull
    @Positive
    private Long stock;

    @NotNull
    @Positive
    private Long remainingQuantity;
}
