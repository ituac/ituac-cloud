package org.ituac.upms.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.ituac.api.upms.model.entity.SysRole;
import org.ituac.api.upms.model.entity.SysRoleMenu;
import org.ituac.common.kern.constant.CacheConstants;
import org.ituac.upms.mapper.UpmsRoleMapper;
import org.ituac.upms.mapper.UpmsRoleMenuMapper;
import org.ituac.upms.service.UpmsRoleService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 服务实现类
 * @author boo
 */

@Service
@RequiredArgsConstructor
public class UpmsRoleServiceImpl extends ServiceImpl<UpmsRoleMapper, SysRole> implements UpmsRoleService
{

    private final UpmsRoleMenuMapper upmsRoleMenuMapper;

    /**
     * 通过用户ID，查询角色信息
     * @param userId
     * @return
     */
    @Override
    public List findRolesByUserId(Integer userId) {
        return baseMapper.listRolesByUserId(userId);
    }

    /**
     * 通过角色ID，删除角色,并清空角色菜单缓存
     * @param id
     * @return
     */
    @Override
    @CacheEvict(value = CacheConstants.MENU_DETAILS, allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public Boolean removeRoleById(Integer id) {
        upmsRoleMenuMapper.delete(Wrappers.<SysRoleMenu>update().lambda().eq(SysRoleMenu::getRoleId, id));
        return this.removeById(id);
    }

}