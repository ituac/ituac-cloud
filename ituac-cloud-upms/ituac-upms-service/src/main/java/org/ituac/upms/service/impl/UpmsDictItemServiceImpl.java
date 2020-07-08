package org.ituac.upms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.ituac.api.upms.model.entity.SysDict;
import org.ituac.api.upms.model.entity.SysDictItem;
import org.ituac.common.kern.constant.CacheConstants;
import org.ituac.common.kern.constant.enums.DictTypeEnum;
import org.ituac.common.kern.util.R;
import org.ituac.upms.mapper.UpmsDictItemMapper;
import org.ituac.upms.service.UpmsDictItemService;
import org.ituac.upms.service.UpmsDictService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * 字典项
 *
 * @author boo
 */

@Service
@RequiredArgsConstructor
public class UpmsDictItemServiceImpl extends ServiceImpl<UpmsDictItemMapper, SysDictItem> implements UpmsDictItemService {

    private final UpmsDictService dictService;

    /**
     * 删除字典项
     * @param id 字典项ID
     * @return
     */
    @Override
    @CacheEvict(value = CacheConstants.DICT_DETAILS, allEntries = true)
    public R removeDictItem(Integer id) {
        // 根据ID查询字典ID
        SysDictItem dictItem = this.getById(id);
        SysDict dict = dictService.getById(dictItem.getDictId());
        // 系统内置
        Assert.state(!DictTypeEnum.SYSTEM.getType().equals(dict.getSystem()), "系统内置字典项目不能删除");
        return R.ok(this.removeById(id));
    }

    /**
     * 更新字典项
     * @param item 字典项
     * @return
     */
    @Override
    @CacheEvict(value = CacheConstants.DICT_DETAILS, allEntries = true)
    public R updateDictItem(SysDictItem item) {
        // 查询字典
        SysDict dict = dictService.getById(item.getDictId());
        // 系统内置
        Assert.state(!DictTypeEnum.SYSTEM.getType().equals(dict.getSystem()), "系统内置字典项目不能修改");
        return R.ok(this.updateById(item));
    }

}