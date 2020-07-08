package org.ituac.upms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.ituac.api.upms.model.entity.SysDeptRelation;

@Mapper
public interface UpmsDeptRelationMapper extends BaseMapper<SysDeptRelation> {

        /**
         * 删除部门关系表数据
         * @param id 部门ID
         */
        void deleteDeptRelationsById(Integer id);

        /**
         * 更改部分关系表数据
         * @param deptRelation
         */
        void updateDeptRelations(SysDeptRelation deptRelation);
}
