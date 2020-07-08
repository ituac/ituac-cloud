package org.ituac.api.upms.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ituac.api.upms.model.entity.SysRole;

/**
 * @author boo
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class RoleDTO extends SysRole {

    /**
     * 角色部门Id
     */
    private Integer roleDeptId;

    /**
     * 部门名称
     */
    private String deptName;

}