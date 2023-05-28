package com.sugako.requests;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class ReceiptUpdateRequest extends ReceiptCreateRequest {

    private Long id;
}
