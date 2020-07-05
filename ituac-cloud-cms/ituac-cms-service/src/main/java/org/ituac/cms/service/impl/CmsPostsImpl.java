package org.ituac.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.ituac.api.cms.model.entity.CmsPosts;
import org.ituac.api.cms.model.entity.PageDto;
import org.ituac.cms.mapper.CmsPostsMapper;
import org.ituac.cms.service.CmsPostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CmsPostsImpl extends ServiceImpl<CmsPostsMapper, CmsPosts> implements CmsPostsService {
    @Autowired
    CmsPostsMapper cmsPostsMapper;

    @Override
    public IPage<CmsPosts> list(PageDto<CmsPosts> pageDto) {
        LambdaQueryWrapper<CmsPosts> queryWrapper = Wrappers.lambdaQuery();
        return cmsPostsMapper.selectPage(pageDto, queryWrapper);
    }
}
