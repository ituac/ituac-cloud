package org.ituac.upms.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.ituac.api.upms.model.dto.MenuTree;
import org.ituac.api.upms.model.entity.SysMenu;
import org.ituac.api.upms.model.entity.SysRoleMenu;
import org.ituac.api.upms.model.vo.MenuVO;
import org.ituac.api.upms.model.vo.TreeUtil;
import org.ituac.common.kern.constant.CacheConstants;
import org.ituac.common.kern.constant.CommonConstants;
import org.ituac.common.kern.constant.enums.MenuTypeEnum;
import org.ituac.common.kern.util.R;
import org.ituac.upms.mapper.UpmsMenuMapper;
import org.ituac.upms.mapper.UpmsRoleMenuMapper;
import org.ituac.upms.service.UpmsMenuService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UpmsMenuServiceImpl extends ServiceImpl<UpmsMenuMapper, SysMenu> implements UpmsMenuService {

    private final UpmsRoleMenuMapper upmsRoleMenuMapper;

    @Override
    @Cacheable(value = CacheConstants.MENU_DETAILS, key = "#roleId  + '_menu'", unless = "#result == null")
    public List<MenuVO> findMenuByRoleId(Integer roleId) {
        return baseMapper.listMenusByRoleId(roleId);
    }

    /**
     * 级联删除菜单
     * @param id 菜单ID
     * @return true成功,false失败
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = CacheConstants.MENU_DETAILS, allEntries = true)
    public R removeMenuById(Integer id) {
        // 查询父节点为当前节点的节点
        List<SysMenu> menuList = this.list(Wrappers.<SysMenu>query().lambda().eq(SysMenu::getParentId, id));

        if (CollUtil.isNotEmpty(menuList)) {
            return R.failed("菜单含有下级不能删除");
        }

        upmsRoleMenuMapper.delete(Wrappers.<SysRoleMenu>query().lambda().eq(SysRoleMenu::getMenuId, id));
        // 删除当前菜单及其子菜单
        return R.ok(this.removeById(id));
    }

    @Override
    @CacheEvict(value = CacheConstants.MENU_DETAILS, allEntries = true)
    public Boolean updateMenuById(SysMenu sysMenu) {
        return this.updateById(sysMenu);
    }

    /**
     * 构建树查询 1. 不是懒加载情况，查询全部 2. 是懒加载，根据parentId 查询 2.1 父节点为空，则查询ID -1
     * @param lazy 是否是懒加载
     * @param parentId 父节点ID
     * @return
     */
    @Override
    public List<MenuTree> treeMenu(boolean lazy, Integer parentId) {
        if (!lazy) {
            return TreeUtil.buildTree(
                    baseMapper.selectList(Wrappers.<SysMenu>lambdaQuery().orderByAsc(SysMenu::getSort)),
                    CommonConstants.MENU_TREE_ROOT_ID);
        }

        Integer parent = parentId == null ? CommonConstants.MENU_TREE_ROOT_ID : parentId;
        return TreeUtil.buildTree(
                baseMapper.selectList(
                        Wrappers.<SysMenu>lambdaQuery().eq(SysMenu::getParentId, parent).orderByAsc(SysMenu::getSort)),
                parent);
    }

    /**
     * 查询菜单
     * @param all 全部菜单
     * @param parentId 父节点ID
     * @return
     */
    @Override
    public List<MenuTree> filterMenu(Set<MenuVO> all, Integer parentId) {
        List<MenuTree> menuTreeList = all.stream().filter(vo -> MenuTypeEnum.LEFT_MENU.getType().equals(vo.getType()))
                .map(MenuTree::new).sorted(Comparator.comparingInt(MenuTree::getSort)).collect(Collectors.toList());
        Integer parent = parentId == null ? CommonConstants.MENU_TREE_ROOT_ID : parentId;
        return TreeUtil.build(menuTreeList, parent);
    }


}
