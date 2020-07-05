package org.ituac.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.ituac.api.cms.model.entity.CmsTags;
import org.ituac.api.cms.model.entity.PageDto;
import org.ituac.cms.mapper.CmsTagsMapper;
import org.ituac.cms.service.CmsTagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CmsTagsServiceImpl extends ServiceImpl<CmsTagsMapper, CmsTags> implements CmsTagsService {
    @Autowired
    CmsTagsMapper cmsTagsMapper;

    @Override
    public IPage<CmsTags> list(PageDto<CmsTags> pageDto) {
        LambdaQueryWrapper<CmsTags> queryWrapper = Wrappers.lambdaQuery();
        return cmsTagsMapper.selectPage(pageDto, queryWrapper);
    }
}
