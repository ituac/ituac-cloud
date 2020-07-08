package org.ituac.upms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.ituac.api.upms.model.entity.SysRole;
import java.util.List;

@Mapper
public interface UpmsRoleMapper extends BaseMapper<SysRole> {

    /**
     * 通过用户ID，查询角色信息
     * @param userId
     * @return
     */
    List<SysRole> listRolesByUserId(Integer userId);

}