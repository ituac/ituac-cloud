package org.ituac.upms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.ituac.api.upms.model.entity.SysUserRole;
/**
 * @author ituac
 */

public interface UpmsUserRoleService extends IService<SysUserRole> {

    /**
     * 根据用户Id删除该用户的角色关系
     * @param userId 用户ID
     */
    Boolean removeRoleByUserId(Integer userId);

}
