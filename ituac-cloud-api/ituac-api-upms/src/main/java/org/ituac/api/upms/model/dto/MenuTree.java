package org.ituac.api.upms.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ituac.api.upms.model.vo.MenuVO;

import java.io.Serializable;

/**
 * @author boo
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class MenuTree extends TreeNode implements Serializable {

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 菜单名称
     */
    private String name;

    private boolean spread = false;

    /**
     * 前端路由标识路径
     */
    private String path;

    /**
     * 路由缓冲
     */
    private String keepAlive;

    /**
     * 权限编码
     */
    private String permission;

    /**
     * 菜单类型 （0菜单 1按钮）
     */
    private String type;

    /**
     * 菜单标签
     */
    private String label;

    /**
     * 排序值
     */
    private Integer sort;

    /**
     * 是否包含子节点
     *
     * @since 3.7
     */
    private Boolean hasChildren;

    public MenuTree() {
    }

    public MenuTree(int id, String name, int parentId) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.label = name;
    }

    public MenuTree(int id, String name, MenuTree parent) {
        this.id = id;
        this.parentId = parent.getId();
        this.name = name;
        this.label = name;
    }

    public MenuTree(MenuVO menuVo) {
        this.id = menuVo.getMenuId();
        this.parentId = menuVo.getParentId();
        this.icon = menuVo.getIcon();
        this.name = menuVo.getName();
        this.path = menuVo.getPath();
        this.type = menuVo.getType();
        this.permission = menuVo.getPermission();
        this.label = menuVo.getName();
        this.sort = menuVo.getSort();
        this.keepAlive = menuVo.getKeepAlive();
    }

}