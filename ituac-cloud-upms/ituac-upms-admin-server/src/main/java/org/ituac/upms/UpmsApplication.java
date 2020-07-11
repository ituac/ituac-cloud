package org.ituac.upms;

import org.ituac.common.security.annotation.EnableItuacFeignClients;
import org.ituac.common.security.annotation.EnableItuacResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author ituac
 */



/**
 * @author boo
 */


@EnableItuacFeignClients
@EnableItuacResourceServer
@SpringCloudApplication
public class UpmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(UpmsApplication.class, args);
    }

}
