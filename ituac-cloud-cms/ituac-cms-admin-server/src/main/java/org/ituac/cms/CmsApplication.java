package org.ituac.cms;

import org.ituac.common.security.annotation.EnableItuacFeignClients;
import org.ituac.common.security.annotation.EnableItuacResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author wp
 */


@EnableItuacFeignClients
@EnableItuacResourceServer
@SpringCloudApplication
public class  CmsApplication{

    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class, args);
    }

}
