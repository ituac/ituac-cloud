package org.ituac.upms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.ituac.api.upms.model.entity.SysLog;

@Mapper
public interface UpmsLogMapper  extends BaseMapper<SysLog> {


}
