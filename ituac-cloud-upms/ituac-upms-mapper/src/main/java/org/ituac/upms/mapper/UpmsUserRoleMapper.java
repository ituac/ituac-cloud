package org.ituac.upms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.ituac.api.upms.model.entity.SysUserRole;

@Mapper
public interface UpmsUserRoleMapper extends BaseMapper<SysUserRole> {

        /**
         * 根据用户Id删除该用户的角色关系
         * @param userId 用户ID
         */
        Boolean deleteByUserId(@Param("userId") Integer userId);

}