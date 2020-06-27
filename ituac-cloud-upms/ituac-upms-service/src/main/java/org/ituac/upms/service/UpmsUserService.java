package org.ituac.upms.service;

import org.ituac.api.upms.model.entity.SysUsers;

public interface UpmsUserService {

    SysUsers create(String username, String password);

    void updateLoginStatusWithOnLine(SysUsers users);

    void updateLoginStatusWithLoginOut(SysUsers users);

    void logintOut(String username);
}