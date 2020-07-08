package org.ituac.api.upms.feign;

import org.ituac.api.upms.feign.factory.RemoteLogServiceFallbackFactory;
import org.ituac.api.upms.model.entity.SysLog;
import org.ituac.common.kern.constant.SecurityConstants;
import org.ituac.common.kern.constant.ServiceNameConstants;
import org.ituac.common.kern.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author boo
 * @date 2020
 */

@FeignClient(contextId = "remoteLogService", value = ServiceNameConstants.UMPS_SERVICE,
        fallbackFactory = RemoteLogServiceFallbackFactory.class)
public interface RemoteLogService {

    /**
     * 保存日志
     * @param sysLog 日志实体
     * @param from 内部调用标志
     * @return succes、false
     */
    @PostMapping("/log")
    R<Boolean> saveLog(@RequestBody SysLog sysLog, @RequestHeader(SecurityConstants.FROM) String from);



}
