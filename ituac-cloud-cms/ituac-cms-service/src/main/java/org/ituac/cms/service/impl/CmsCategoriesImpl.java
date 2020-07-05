package org.ituac.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.ituac.api.cms.model.entity.CmsCategories;
import org.ituac.api.cms.model.entity.PageDto;
import org.ituac.cms.mapper.CmsCategoriesMapper;
import org.ituac.cms.service.CmsCategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CmsCategoriesImpl extends ServiceImpl<CmsCategoriesMapper, CmsCategories> implements CmsCategoriesService {

    @Autowired
    CmsCategoriesMapper cmsCategoriesMapper;

    @Override
    public IPage<CmsCategories> list(PageDto<CmsCategories> pageDto) {
        LambdaQueryWrapper<CmsCategories> queryWrapper = Wrappers.lambdaQuery();
        return cmsCategoriesMapper.selectPage(pageDto, queryWrapper);
    }
}
