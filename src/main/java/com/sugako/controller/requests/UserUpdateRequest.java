package com.sugako.controller.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {
    private Long id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private Long rolesId;
    private Timestamp created;
    private Timestamp changed;
    private Boolean isDeleted;
}
