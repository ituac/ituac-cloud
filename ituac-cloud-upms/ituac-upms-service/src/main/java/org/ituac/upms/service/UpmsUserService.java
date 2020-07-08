package org.ituac.upms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.ituac.api.upms.model.dto.UserDTO;
import org.ituac.api.upms.model.dto.UserInfo;
import org.ituac.api.upms.model.entity.SysUser;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.ituac.api.upms.model.entity.SysUsers;
import org.ituac.api.upms.model.vo.UserVO;
import org.ituac.common.kern.util.R;
import java.util.List;
/**
 * @author ituac
 */


public interface UpmsUserService extends IService<SysUser> {

    SysUsers create(String username, String password);

    void updateLoginStatusWithOnLine(SysUsers users);

    void updateLoginStatusWithLoginOut(SysUsers users);

    void logintOut(String username);


    /**
     * 查询用户信息
     * @param sysUser 用户
     * @return userInfo
     */
    UserInfo getUserInfo(SysUser sysUser);

    /**
     * 分页查询用户信息（含有角色信息）
     * @param page 分页对象
     * @param userDTO 参数列表
     * @return
     */
    IPage getUserWithRolePage(Page page, UserDTO userDTO);

    /**
     * 删除用户
     * @param sysUser 用户
     * @return boolean
     */
    Boolean removeUserById(SysUser sysUser);

    /**
     * 更新当前用户基本信息
     * @param userDto 用户信息
     * @return Boolean
     */
    R updateUserInfo(UserDTO userDto);

    /**
     * 更新指定用户信息
     * @param userDto 用户信息
     * @return
     */
    Boolean updateUser(UserDTO userDto);

    /**
     * 通过ID查询用户信息
     * @param id 用户ID
     * @return 用户信息
     */
    UserVO getUserVoById(Integer id);

    /**
     * 查询上级部门的用户信息
     * @param username 用户名
     * @return R
     */
    List<SysUser> listAncestorUsersByUsername(String username);

    /**
     * 保存用户信息
     * @param userDto DTO 对象
     * @return success/fail
     */
    Boolean saveUser(UserDTO userDto);


}