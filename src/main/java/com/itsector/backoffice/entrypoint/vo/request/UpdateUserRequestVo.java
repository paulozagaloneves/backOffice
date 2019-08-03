package com.itsector.backoffice.entrypoint.vo.request;

import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
public class UpdateUserRequestVo {

    @NotBlank
    private String name;

    @NotBlank
    private String password;
}
