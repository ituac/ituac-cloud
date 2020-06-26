package org.ituac.upms.service.impl;

import org.ituac.api.upms.model.entity.SysUsers;
import org.ituac.upms.mapper.UpmsUserMapper;
import org.ituac.upms.service.UpmsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UpmsUserService {

    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UpmsUserMapper upmsUserMapper;

    @Override
    public SysUsers create(String username, String password) {
        SysUsers user = new SysUsers();
        user.setUsername(username);
        password = "{bcrypt}" + passwordEncoder.encode(password);
        user.setPassword(password);
        SysUsers u = upmsUserMapper.save(user);
        return u;
    }

}
