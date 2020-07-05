package org.ituac.cms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.ituac.api.cms.model.entity.CmsCategories;
import org.ituac.api.cms.model.entity.PageDto;

public interface CmsCategoriesService extends IService<CmsCategories> {
    IPage<CmsCategories> list(PageDto<CmsCategories> pageDto);
}
