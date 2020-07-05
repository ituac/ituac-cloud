package org.ituac.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.ituac.api.cms.model.entity.CmsPosts;

@Mapper
public interface CmsPostsMapper extends BaseMapper<CmsPosts> {
}
