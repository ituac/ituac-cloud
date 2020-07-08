package org.ituac.upms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.ituac.api.upms.model.entity.SysOauthClientDetails;
import org.ituac.common.kern.constant.CacheConstants;
import org.ituac.upms.mapper.UpmsOauthClientDetailsMapper;
import org.ituac.upms.service.UpmsOauthClientDetailsService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 * @author boo
 */

@Service
public class UpmsOauthClientDetailsServiceImpl extends ServiceImpl<UpmsOauthClientDetailsMapper, SysOauthClientDetails>
        implements UpmsOauthClientDetailsService {

    /**
     * 通过ID删除客户端
     * @param id
     * @return
     */
    @Override
    @CacheEvict(value = CacheConstants.CLIENT_DETAILS_KEY, key = "#id")
    public Boolean removeClientDetailsById(String id) {
        return this.removeById(id);
    }

    /**
     * 根据客户端信息
     * @param clientDetails
     * @return
     */
    @Override
    @CacheEvict(value = CacheConstants.CLIENT_DETAILS_KEY, key = "#clientDetails.clientId")
    public Boolean updateClientDetailsById(SysOauthClientDetails clientDetails) {
        return this.updateById(clientDetails);
    }

}
