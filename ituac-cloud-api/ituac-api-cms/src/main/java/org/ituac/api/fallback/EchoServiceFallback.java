package org.ituac.api.fallback;

import org.ituac.api.feign.RemoteEchoServiceFeign;
import org.springframework.stereotype.Component;

@Component
public class EchoServiceFallback implements RemoteEchoServiceFeign {

    @Override
    public String echo(String title) {
        return "网络失败";
    }
}
