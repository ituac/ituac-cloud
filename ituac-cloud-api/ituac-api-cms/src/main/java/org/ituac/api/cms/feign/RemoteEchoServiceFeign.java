package org.ituac.api.cms.feign;

import org.ituac.api.cms.fallback.EchoServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author ituac
 */

@FeignClient(value = "ituac-cms-admin-server",fallback = EchoServiceFallback.class)
public interface RemoteEchoServiceFeign {

    @GetMapping("/cms/echo/feign/info/{title}")
    String echo(@PathVariable("title") String title);

}
