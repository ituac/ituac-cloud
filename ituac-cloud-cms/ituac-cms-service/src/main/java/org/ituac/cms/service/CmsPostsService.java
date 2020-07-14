package org.ituac.cms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.ituac.api.cms.model.entity.Posts;
import org.ituac.api.cms.model.entity.PageDto;

public interface CmsPostsService extends IService<Posts> {
    IPage<Posts> list(PageDto<Posts> pageDto);
}
