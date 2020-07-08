package org.ituac.upms.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.ituac.api.upms.model.entity.SysDict;
import org.ituac.api.upms.model.entity.SysDictItem;
import org.ituac.common.kern.constant.CacheConstants;
import org.ituac.common.kern.constant.enums.DictTypeEnum;
import org.ituac.upms.mapper.UpmsDictItemMapper;
import org.ituac.upms.mapper.UpmsDictMapper;
import org.ituac.upms.service.UpmsDictService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * 字典表
 *
 * @author boo
 */

@Service
@RequiredArgsConstructor
public class UpmsDictServiceImpl extends ServiceImpl<UpmsDictMapper, SysDict> implements UpmsDictService {

    private final UpmsDictItemMapper upmsdictItemMapper;

    /**
     * 根据ID 删除字典
     * @param id 字典ID
     * @return
     */
    @Override
    @CacheEvict(value = CacheConstants.DICT_DETAILS, allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void removeDict(Integer id) {
        SysDict dict = this.getById(id);
        // 系统内置
        Assert.state(!DictTypeEnum.SYSTEM.getType().equals(dict.getSystem()), "系统内置字典项目不能删除");
        baseMapper.deleteById(id);
        upmsdictItemMapper.delete(Wrappers.<SysDictItem>lambdaQuery().eq(SysDictItem::getDictId, id));
    }

    /**
     * 更新字典
     * @param dict 字典
     * @return
     */
    @Override
    public void updateDict(SysDict dict) {
        SysDict sysDict = this.getById(dict.getId());
        // 系统内置
        Assert.state(!DictTypeEnum.SYSTEM.getType().equals(sysDict.getSystem()), "系统内置字典项目不能修改");
        this.updateById(dict);
    }

}

