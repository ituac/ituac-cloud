package org.ituac.upms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.ituac.api.upms.model.entity.SysDict;

/**
 * @author ituac
 */


public interface UpmsDictService extends IService<SysDict> {

    /**
     * 根据ID 删除字典
     * @param id
     * @return
     */
    void removeDict(Integer id);

    /**
     * 更新字典
     * @param sysDict 字典
     * @return
     */
    void updateDict(SysDict sysDict);

}