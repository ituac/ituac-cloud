package org.ituac.api.upms.model.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ituac.api.upms.model.entity.SysUser;

import java.util.List;

/**
 * @author boo
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserDTO extends SysUser {

    /**
     * 角色ID
     */
    private List<Integer> role;

    private Integer deptId;

    /**
     * 新密码
     */
    private String newpassword1;

}
