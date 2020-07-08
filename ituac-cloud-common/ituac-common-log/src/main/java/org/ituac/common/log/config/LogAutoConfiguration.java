package org.ituac.common.log.config;

import lombok.RequiredArgsConstructor;
import org.ituac.api.upms.feign.RemoteLogService;
import org.ituac.common.log.aspect.SysLogAspect;
import org.ituac.common.log.aspect.event.SysLogListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author boo
 * 日志自动装配
 */

@EnableAsync
@RequiredArgsConstructor
@ConditionalOnWebApplication
@Configuration(proxyBeanMethods = false)
public class LogAutoConfiguration {


        private final RemoteLogService remoteLogService;

        @Bean
        public SysLogListener sysLogListener() {
            return new SysLogListener(remoteLogService);
        }

        @Bean
        public SysLogAspect sysLogAspect() {
            return new SysLogAspect();
        }


}
