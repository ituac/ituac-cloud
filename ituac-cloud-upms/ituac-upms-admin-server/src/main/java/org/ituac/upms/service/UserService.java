package org.ituac.upms.service;

import org.ituac.upms.entity.User;

public interface UserService {

    public User create(String username, String password);

}