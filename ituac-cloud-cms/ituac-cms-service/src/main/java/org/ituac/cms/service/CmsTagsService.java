package org.ituac.cms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.ituac.api.cms.model.entity.CmsTags;
import org.ituac.api.cms.model.entity.PageDto;

public interface CmsTagsService extends IService<CmsTags> {

    IPage<CmsTags> list(PageDto<CmsTags> pageDto);
}
