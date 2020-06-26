package org.ituac.upms.service;

import org.ituac.api.upms.model.entity.SysUsers;

public interface UpmsUserService {

    public SysUsers create(String username, String password);

}