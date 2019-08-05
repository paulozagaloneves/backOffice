package com.itsector.backoffice.domain;

import lombok.Builder;
import lombok.Value;

import java.util.Date;

@Builder
@Value
public class User {

    private Integer id;
    private String name;
    private String userName;
    private String password;
    private Date createTimestamp;
    private Date updateTimestamp;
    private String role;
}
