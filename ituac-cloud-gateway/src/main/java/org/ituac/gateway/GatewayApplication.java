package org.ituac.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

@SpringCloudApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    /**
     * 资源过滤器
     * @return 资源过滤器
     */
    /*@Bean
    public AccessFilter accessFilter(){
        return new AccessFilter();
    }*/

}
