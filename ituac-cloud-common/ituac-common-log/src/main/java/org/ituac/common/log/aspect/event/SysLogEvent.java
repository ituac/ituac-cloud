package org.ituac.common.log.aspect.event;

import org.ituac.api.upms.model.entity.SysLog;
import org.springframework.context.ApplicationEvent;

/**
 * @author boo
 * 系统日志事件
 */
public class SysLogEvent extends ApplicationEvent {

    public SysLogEvent(SysLog source) {
        super(source);
    }

}