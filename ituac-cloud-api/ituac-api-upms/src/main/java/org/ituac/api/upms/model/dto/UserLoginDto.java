package org.ituac.api.upms.model.dto;

import org.ituac.api.upms.model.entity.JWT;
import org.ituac.api.upms.model.entity.SysUsers;

public class UserLoginDto {

    private JWT jwt;
    private SysUsers user;

    public JWT getJwt() {
        return jwt;
    }

    public void setJwt(JWT jwt) {
        this.jwt = jwt;
    }

    public SysUsers getUser() {
        return user;
    }

    public void setUser(SysUsers user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserLoginDto{" +
                "jwt=" + jwt +
                ", user=" + user +
                '}';
    }

}
