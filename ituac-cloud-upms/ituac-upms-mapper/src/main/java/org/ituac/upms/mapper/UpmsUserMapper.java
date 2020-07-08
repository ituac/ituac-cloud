package org.ituac.upms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import feign.Param;
import org.apache.ibatis.annotations.Mapper;
import org.ituac.api.upms.model.dto.UserDTO;
import org.ituac.api.upms.model.entity.SysUser;
import org.ituac.api.upms.model.entity.SysUsers;
import org.ituac.api.upms.model.vo.UserVO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 用户表 Mapper 接口
 * @author boo
 */

@Mapper
public interface UpmsUserMapper extends BaseMapper<SysUser> {

    //SysUsers findByUsername(String username);

    /**
     * 通过用户名查询用户信息（含有角色信息）
     * @param username 用户名
     * @return userVo
     */
    UserVO getUserVoByUsername(String username);

    /**
     * 分页查询用户信息（含角色）
     * @param page 分页
     * @param userDTO 查询参数
     * @return list
     */
    IPage<List<UserVO>> getUserVosPage(Page page, @Param("query") UserDTO userDTO);

    /**
     * 通过ID查询用户信息
     * @param id 用户ID
     * @return userVo
     */
    UserVO getUserVoById(Integer id);

}