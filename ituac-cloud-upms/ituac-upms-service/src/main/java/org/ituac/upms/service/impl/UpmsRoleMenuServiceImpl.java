package org.ituac.upms.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.ituac.api.upms.model.entity.SysRoleMenu;
import org.ituac.common.kern.constant.CacheConstants;
import org.ituac.upms.mapper.UpmsRoleMenuMapper;
import org.ituac.upms.service.UpmsRoleMenuService;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色菜单表 服务实现类
 * @author boo
 */

@Service
@RequiredArgsConstructor
public class UpmsRoleMenuServiceImpl extends ServiceImpl<UpmsRoleMenuMapper, SysRoleMenu> implements UpmsRoleMenuService {

    private final CacheManager cacheManager;

    /**
     * @param role
     * @param roleId 角色
     * @param menuIds 菜单ID拼成的字符串，每个id之间根据逗号分隔
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = CacheConstants.MENU_DETAILS, key = "#roleId + '_menu'")
    public Boolean saveRoleMenus(String role, Integer roleId, String menuIds) {
        this.remove(Wrappers.<SysRoleMenu>query().lambda().eq(SysRoleMenu::getRoleId, roleId));

        if (StrUtil.isBlank(menuIds)) {
            return Boolean.TRUE;
        }
        List<SysRoleMenu> roleMenuList = Arrays.stream(menuIds.split(",")).map(menuId -> {
            SysRoleMenu roleMenu = new SysRoleMenu();
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(Integer.valueOf(menuId));
            return roleMenu;
        }).collect(Collectors.toList());

        // 清空userinfo
        cacheManager.getCache(CacheConstants.USER_DETAILS).clear();
        return this.saveBatch(roleMenuList);
    }

}