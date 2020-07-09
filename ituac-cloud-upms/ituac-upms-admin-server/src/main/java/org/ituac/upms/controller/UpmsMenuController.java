package org.ituac.upms.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.ituac.api.upms.model.entity.SysMenu;
import org.ituac.api.upms.model.vo.MenuVO;
import org.ituac.common.kern.util.R;
import org.ituac.common.log.annotation.SysLog;
import org.ituac.common.security.utils.SecurityUtils;
import org.ituac.upms.service.UpmsMenuService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author boo
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
@Api(value = "menu", tags = "菜单管理模块")
public class UpmsMenuController {

    private final UpmsMenuService upmsMenuService;

    /**
     * 返回当前用户的树形菜单集合
     * @param parentId 父节点ID
     * @return 当前用户的树形菜单
     */
    @GetMapping
    public R getUserMenu(Integer parentId) {

        // 获取符合条件的菜单
        Set<MenuVO> all = new HashSet<>();
        SecurityUtils.getRoles().forEach(roleId -> all.addAll(upmsMenuService.findMenuByRoleId(roleId)));
        return R.ok(upmsMenuService.filterMenu(all, parentId));
    }

    /**
     * 返回树形菜单集合
     * @param lazy 是否是懒加载
     * @param parentId 父节点ID
     * @return 树形菜单
     */
    @GetMapping(value = "/tree")
    public R getTree(boolean lazy, Integer parentId) {
        return R.ok(upmsMenuService.treeMenu(lazy, parentId));
    }

    /**
     * 返回角色的菜单集合
     * @param roleId 角色ID
     * @return 属性集合
     */
    @GetMapping("/tree/{roleId}")
    public R getRoleTree(@PathVariable Integer roleId) {
        return R.ok(
                upmsMenuService.findMenuByRoleId(roleId).stream().map(MenuVO::getMenuId).collect(Collectors.toList()));
    }

    /**
     * 通过ID查询菜单的详细信息
     * @param id 菜单ID
     * @return 菜单详细信息
     */
    @GetMapping("/{id}")
    public R getById(@PathVariable Integer id) {
        return R.ok(upmsMenuService.getById(id));
    }

    /**
     * 新增菜单
     * @param sysMenu 菜单信息
     * @return 含ID 菜单信息
     */
    @SysLog("新增菜单")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('sys_menu_add')")
    public R save(@Valid @RequestBody SysMenu sysMenu) {
        upmsMenuService.save(sysMenu);
        return R.ok(sysMenu);
    }

    /**
     * 删除菜单
     * @param id 菜单ID
     * @return success/false
     */
    @SysLog("删除菜单")
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('sys_menu_del')")
    public R removeById(@PathVariable Integer id) {
        return upmsMenuService.removeMenuById(id);
    }

    /**
     * 更新菜单
     * @param sysMenu
     * @return
     */
    @SysLog("更新菜单")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('sys_menu_edit')")
    public R update(@Valid @RequestBody SysMenu sysMenu) {
        return R.ok(upmsMenuService.updateMenuById(sysMenu));
    }

}