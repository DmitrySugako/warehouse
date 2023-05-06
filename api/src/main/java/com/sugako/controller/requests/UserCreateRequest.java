package com.sugako.controller.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class UserCreateRequest {

    private String name;
    private String surname;
    private String login;
    private String password;
    private Long rolesId;
}
