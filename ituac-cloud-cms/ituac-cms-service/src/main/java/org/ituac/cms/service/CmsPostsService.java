package org.ituac.cms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.ituac.api.cms.model.entity.CmsPosts;
import org.ituac.api.cms.model.entity.PageDto;

public interface CmsPostsService extends IService<CmsPosts> {
    IPage<CmsPosts> list(PageDto<CmsPosts> pageDto);
}
