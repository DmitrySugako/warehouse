package com.sugako.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class StockUpdateRequest extends StockCreateRequest {

    private Long id;
}
