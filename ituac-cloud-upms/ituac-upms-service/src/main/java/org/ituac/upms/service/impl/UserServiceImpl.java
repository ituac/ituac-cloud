package org.ituac.upms.service.impl;

import org.ituac.api.upms.model.entity.SysUsers;
import org.ituac.upms.mapper.UpmsUserMapper;
import org.ituac.upms.service.UpmsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    @Override
    public void updateLoginStatusWithOnLine(SysUsers users) {
        users.setLoginStatus(1);
        upmsUserMapper.saveAndFlush(users);
    }

    @Override
    public void updateLoginStatusWithLoginOut(SysUsers users) {
        users.setLoginStatus(0);
        upmsUserMapper.saveAndFlush(users);
    }

    @Override
    public void logintOut(String username) {
        SysUsers users = upmsUserMapper.findByUsername(username);
        if(users == null){
            throw new RuntimeException("退出登录异常，用户不存在，用户名 ->" + username);
        }
        //修改用户登录状态 改为登出状态
        updateLoginStatusWithLoginOut(users);
    }

}
