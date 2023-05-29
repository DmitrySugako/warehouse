package com.sugako.requests;


import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Object for forming a shipment line")
public class ShipmentCreateRequest {


    @NotNull
    @Positive
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED,example = "shipment number",type = "Long", description = "shipment number")
    private Long shipmentNumber;

    @NotNull
    @Positive
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED,example = "stock",type = "Long", description = "stock id number")
    private Long stock;

    @NotNull
    @Positive
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED,example = "remaining quantity",type = "Long", description = "number for shipping")
    private Long remainingQuantity;
}
