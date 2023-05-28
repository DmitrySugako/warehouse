package com.sugako.requests;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;


@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Validated
public class ItemCreateRequest implements Serializable {


    @NotNull
    @Size(min = 3, max = 100)
    private String skuCode;

    @NotNull
    @Size(min = 4, max = 250)
    private String description;

    @NotNull
    @Size(min = 3, max = 100)
    private String category;


}
