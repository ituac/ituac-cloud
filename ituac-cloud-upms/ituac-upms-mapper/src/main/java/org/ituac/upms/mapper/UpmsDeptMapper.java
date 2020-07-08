package org.ituac.upms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.ituac.api.upms.model.entity.SysDept;
import java.util.List;

@Mapper
public interface UpmsDeptMapper extends BaseMapper<SysDept> {


    /**
     * 关联dept——relation
     * @return 数据列表
     */
    List<SysDept> listDepts();

}
