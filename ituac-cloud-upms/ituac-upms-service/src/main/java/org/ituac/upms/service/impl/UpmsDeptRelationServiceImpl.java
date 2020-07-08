package org.ituac.upms.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.ituac.api.upms.model.entity.SysDept;
import org.ituac.api.upms.model.entity.SysDeptRelation;
import org.ituac.api.upms.model.entity.SysUser;
import org.ituac.upms.mapper.UpmsDeptMapper;
import org.ituac.upms.mapper.UpmsDeptRelationMapper;
import org.ituac.upms.service.UpmsDeptRelationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UpmsDeptRelationServiceImpl  extends ServiceImpl<UpmsDeptRelationMapper, SysDeptRelation> implements UpmsDeptRelationService {

    private final UpmsDeptRelationMapper sysDeptRelationMapper;

    /**
     * 维护部门关系
     * @param sysDept 部门
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveDeptRelation(SysDept sysDept) {
        // 增加部门关系表
        SysDeptRelation condition = new SysDeptRelation();
        condition.setDescendant(sysDept.getParentId());
        List<SysDeptRelation> relationList = sysDeptRelationMapper.selectList(
                Wrappers.<SysDeptRelation>query().lambda().eq(SysDeptRelation::getDescendant, sysDept.getParentId()))
                .stream().map(relation -> {
                    relation.setDescendant(sysDept.getDeptId());
                    return relation;
                }).collect(Collectors.toList());
        if (CollUtil.isNotEmpty(relationList)) {
            this.saveBatch(relationList);
        }

        // 自己也要维护到关系表中
        SysDeptRelation own = new SysDeptRelation();
        own.setDescendant(sysDept.getDeptId());
        own.setAncestor(sysDept.getDeptId());
        sysDeptRelationMapper.insert(own);
    }

    /**
     * 通过ID删除部门关系
     * @param id
     */
    @Override
    public void removeDeptRelationById(Integer id) {
        baseMapper.deleteDeptRelationsById(id);
    }

    /**
     * 更新部门关系
     * @param relation
     */
    @Override
    public void updateDeptRelation(SysDeptRelation relation) {
        baseMapper.updateDeptRelations(relation);
    }

}
