package org.ituac.upms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author ituac
 */

@EnableDiscoveryClient
@EnableFeignClients("org.ituac.api")
@EntityScan("org.ituac.api.upms.model")
@SpringBootApplication(scanBasePackages = {"org.ituac"})
public class UpmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(UpmsApplication.class, args);
    }

}
