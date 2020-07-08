package org.ituac.common.log.aspect.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ituac.api.upms.feign.RemoteLogService;
import org.ituac.api.upms.model.entity.SysLog;
import org.ituac.common.kern.constant.SecurityConstants;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

/**
 * @author lengleng 异步监听日志事件
 */
@Slf4j
@RequiredArgsConstructor
public class SysLogListener {

    private final RemoteLogService remoteLogService;

    @Async
    @Order
    @EventListener(SysLogEvent.class)
    public void saveSysLog(SysLogEvent event) {
        SysLog logs = (SysLog) event.getSource();
        remoteLogService.saveLog(logs, SecurityConstants.FROM_IN);
    }

}