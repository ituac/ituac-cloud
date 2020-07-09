package org.ituac.api.upms.feign.factory;

import feign.hystrix.FallbackFactory;
import org.ituac.api.upms.feign.RemoteTokenService;
import org.ituac.api.upms.feign.fallback.RemoteTokenServiceFallbackImpl;
import org.springframework.stereotype.Component;

/**
 * @author boo
 */

@Component
public class RemoteTokenServiceFallbackFactory implements FallbackFactory<RemoteTokenService> {

    @Override
    public RemoteTokenService create(Throwable throwable) {
        RemoteTokenServiceFallbackImpl remoteTokenServiceFallback = new RemoteTokenServiceFallbackImpl();
        remoteTokenServiceFallback.setCause(throwable);
        return remoteTokenServiceFallback;
    }

}
