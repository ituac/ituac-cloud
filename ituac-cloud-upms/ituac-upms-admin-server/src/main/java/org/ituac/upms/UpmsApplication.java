package org.ituac.upms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author ituac
 */

@EnableDiscoveryClient
@EnableFeignClients("org.ituac.api")
@SpringBootApplication(scanBasePackages = {"org.ituac","org.ituac.api.dubbo"})
public class UpmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(UpmsApplication.class, args);
    }

}
