package org.ituac.upms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.ituac.api.upms.model.entity.SysUserRole;
import org.ituac.upms.mapper.UpmsUserRoleMapper;
import org.ituac.upms.service.UpmsUserRoleService;
import org.springframework.stereotype.Service;

/**
 * 用户角色表 服务实现类
 * @author boo
 */

@Service
public class UpmsUserRoleServiceImpl extends ServiceImpl<UpmsUserRoleMapper, SysUserRole> implements UpmsUserRoleService {

    /**
     * 根据用户Id删除该用户的角色关系
     * @param userId 用户ID
     */
    @Override
    public Boolean removeRoleByUserId(Integer userId) {
        return baseMapper.deleteByUserId(userId);
    }

}