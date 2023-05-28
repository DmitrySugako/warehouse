package com.sugako.requests;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class AddressUpdateRequest extends AddressCreateRequest {

    private Long id;
}
