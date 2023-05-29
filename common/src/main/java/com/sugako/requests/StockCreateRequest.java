package com.sugako.requests;


import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "An object for setting up a receipt, a connecting item with a receipt order")
public class StockCreateRequest {

    @NotNull
    @Positive
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED,example = "receipt",type = "Long", description = "receipt id number")
    private Long receipt;

    @NotNull
    @Positive
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED,example = "item",type = "Long", description = "item id number")
    private Long item;


    @NotNull
    @Positive
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED,example = "ordered quantity",type = "Long", description = "item ordered quantity")
    private Long orderedQuantity;


    @NotNull
    @PositiveOrZero
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED,example = "available quantity",type = "Long", description = "item available quantity")
    private Long availableQuantity;


    @NotNull
    @PositiveOrZero
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED,example = "reserved quantity",type = "Long", description = "item reserved quantity")
    private Long reservedQuantity;
}
