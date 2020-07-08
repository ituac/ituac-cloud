package org.ituac.common.security.service;

import org.ituac.common.kern.constant.CacheConstants;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import lombok.SneakyThrows;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import javax.sql.DataSource;

/**
 * @author boo
 * see JdbcClientDetailsService
 */
public class ItuacClientDetailsService extends JdbcClientDetailsService {

    public ItuacClientDetailsService(DataSource dataSource) {
        super(dataSource);
    }

    /**
     * 重写原生方法支持redis缓存
     * @param clientId
     * @return
     */
    @Override
    @SneakyThrows
    @Cacheable(value = CacheConstants.CLIENT_DETAILS_KEY, key = "#clientId", unless = "#result == null")
    public ClientDetails loadClientByClientId(String clientId) {
        return super.loadClientByClientId(clientId);
    }


}
