package org.ituac.api.upms.model.dto;

import javax.validation.constraints.*;
import java.io.Serializable;


public class UserLoginParamDto implements Serializable {

    @NotNull(message = "用户名不能为空")
    private String username;

    @NotNull(message = "密码不能为空")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
