package com.sugako.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Validated
public class ReceiptCreateRequest {

    @Size(min = 4, max = 100)
    @NotNull
    private String receiptNumber;
}
