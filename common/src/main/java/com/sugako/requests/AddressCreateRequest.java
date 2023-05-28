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
public class AddressCreateRequest {

    @Size(min = 2, max = 20)
    @NotNull
    private String cellAddress;
}
