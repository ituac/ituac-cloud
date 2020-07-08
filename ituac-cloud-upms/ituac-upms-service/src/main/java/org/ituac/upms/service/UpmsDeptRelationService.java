package org.ituac.upms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.ituac.api.upms.model.entity.SysDept;
import org.ituac.api.upms.model.entity.SysDeptRelation;

/**
 * @author ituac
 */

public interface UpmsDeptRelationService extends IService<SysDeptRelation> {

    /**
     * 新建部门关系
     * @param sysDept 部门
     */
    void saveDeptRelation(SysDept sysDept);

    /**
     * 通过ID删除部门关系
     * @param id
     */
    void removeDeptRelationById(Integer id);

    /**
     * 更新部门关系
     * @param relation
     */
    void updateDeptRelation(SysDeptRelation relation);


}
