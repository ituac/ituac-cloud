package org.ituac.cms;

//import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author ituac
 */


@EnableDiscoveryClient
//@EnableDubbo(scanBasePackages = "org.ituac.cms.service.impl")
@EnableFeignClients("org.ituac.api")
@ComponentScan(basePackages = {"org.ituac.*"})  //指定扫描包路径
@SpringBootApplication
public class  CmsApplication{

    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class, args);
    }

}
