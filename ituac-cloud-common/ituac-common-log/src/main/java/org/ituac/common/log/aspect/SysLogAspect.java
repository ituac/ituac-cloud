package org.ituac.common.log.aspect;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.ituac.common.kern.util.SpringContextHolder;
import org.ituac.common.log.annotation.SysLog;
import org.ituac.common.log.aspect.event.SysLogEvent;
import org.ituac.common.log.utils.SysLogUtil;

/**
 * 操作日志使用spring event异步入库
 *
 * @author L.cm
 */
@Aspect
@Slf4j
public class SysLogAspect {

    @Around("@annotation(sysLog)")
    @SneakyThrows
    public Object around(ProceedingJoinPoint point, SysLog sysLog) {
        String strClassName = point.getTarget().getClass().getName();
        String strMethodName = point.getSignature().getName();
        log.debug("[类名]:{},[方法]:{}", strClassName, strMethodName);

        org.ituac.api.upms.model.entity.SysLog logs = SysLogUtil.getSysLog();
        logs.setTitle(sysLog.value());
        // 发送异步日志事件
        Long startTime = System.currentTimeMillis();
        Object obj = point.proceed();
        Long endTime = System.currentTimeMillis();
        logs.setTime(endTime - startTime);
        SpringContextHolder.publishEvent(new SysLogEvent(logs));
        return obj;
    }


}
