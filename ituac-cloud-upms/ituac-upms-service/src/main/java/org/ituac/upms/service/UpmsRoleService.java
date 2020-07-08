package org.ituac.upms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.ituac.api.upms.model.entity.SysRole;

import java.util.List;
/**
 * @author ituac
 */

public interface UpmsRoleService extends IService<SysRole> {

    /**
     * 通过用户ID，查询角色信息
     * @param userId
     * @return
     */
    List<SysRole> findRolesByUserId(Integer userId);

    /**
     * 通过角色ID，删除角色
     * @param id
     * @return
     */
    Boolean removeRoleById(Integer id);

}
