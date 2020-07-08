package org.ituac.upms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.ituac.api.upms.model.entity.SysDictItem;
import org.ituac.common.kern.util.R;

/**
 * @author ituac
 */


public interface UpmsDictItemService extends IService<SysDictItem> {

        /**
         * 删除字典项
         * @param id 字典项ID
         * @return
         */
        R removeDictItem(Integer id);

        /**
         * 更新字典项
         * @param item 字典项
         * @return
         */
        R updateDictItem(SysDictItem item);
}
