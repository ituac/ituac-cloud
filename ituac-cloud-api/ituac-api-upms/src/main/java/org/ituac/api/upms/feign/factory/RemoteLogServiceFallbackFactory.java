package org.ituac.api.upms.feign.factory;

import feign.hystrix.FallbackFactory;
import org.ituac.api.upms.feign.RemoteLogService;
import org.ituac.api.upms.feign.fallback.RemoteLogServiceFallbackImpl;

public class RemoteLogServiceFallbackFactory implements FallbackFactory<RemoteLogService> {

    @Override
    public RemoteLogService create(Throwable throwable) {
        RemoteLogServiceFallbackImpl remoteLogServiceFallback = new RemoteLogServiceFallbackImpl();
        remoteLogServiceFallback.setCause(throwable);
        return remoteLogServiceFallback;
    }


}
