package com.itsector.backoffice.entrypoint.vo;

import com.itsector.backoffice.domain.User;
import lombok.Builder;
import lombok.Value;

import java.util.Date;

@Value
public class UserVo {

    private Integer id;
    private String name;
    private String userName;
    private Date createTimestamp;
    private Date updateTimestamp;

    public UserVo(User user) {
        this.id = user.getId();
        this.name = user.getName();;
        this.userName = user.getUserName();
        this.createTimestamp = user.getCreateTimestamp();
        this.updateTimestamp = user.getUpdateTimestamp();
    }
}
