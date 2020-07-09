package org.ituac.api.upms.feign;

import org.ituac.api.upms.feign.factory.RemoteTokenServiceFallbackFactory;
import org.ituac.common.kern.constant.SecurityConstants;
import org.ituac.common.kern.constant.ServiceNameConstants;
import org.ituac.common.kern.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author boo
 */

@FeignClient(contextId = "remoteTokenService", value = ServiceNameConstants.AUTH_SERVICE,
        fallbackFactory = RemoteTokenServiceFallbackFactory.class)
public interface RemoteTokenService {

    /**
     * 分页查询token 信息
     * @param params 分页参数
     * @param from 内部调用标志
     * @return page
     */
    @PostMapping("/token/page")
    R getTokenPage(@RequestBody Map<String, Object> params, @RequestHeader(SecurityConstants.FROM) String from);

    /**
     * 删除token
     * @param token token
     * @param from 调用标志
     * @return
     */
    @DeleteMapping("/token/{token}")
    R<Boolean> removeToken(@PathVariable("token") String token, @RequestHeader(SecurityConstants.FROM) String from);

}
