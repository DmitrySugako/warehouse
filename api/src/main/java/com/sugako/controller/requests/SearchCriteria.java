package com.sugako.controller.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class SearchCriteria {

    @NotNull
    @Size(min = 1, max = 100)
    private String query;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
