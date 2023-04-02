package com.sugako.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.sql.Timestamp;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class User {

    private Long id;

    private String name;

    private String surname;

    private String login;

    private String password;

    private Long rolesId;

    private Timestamp created;

    private Timestamp changed;

    private Boolean isDeleted;

    public User (String name, String surname, String login, String password, Long rolesId) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.rolesId = rolesId;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
