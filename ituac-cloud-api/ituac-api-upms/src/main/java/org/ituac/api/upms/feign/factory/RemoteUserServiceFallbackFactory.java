package org.ituac.api.upms.feign.factory;

import feign.hystrix.FallbackFactory;
import org.ituac.api.upms.feign.RemoteUserService;
import org.ituac.api.upms.feign.fallback.RemoteUserServiceFallbackImpl;
import org.springframework.stereotype.Component;

/**
 * @author boo
 */
@Component
public class RemoteUserServiceFallbackFactory implements FallbackFactory<RemoteUserService> {

    @Override
    public RemoteUserService create(Throwable throwable) {
        RemoteUserServiceFallbackImpl remoteUserServiceFallback = new RemoteUserServiceFallbackImpl();
        remoteUserServiceFallback.setCause(throwable);
        return remoteUserServiceFallback;
    }

}