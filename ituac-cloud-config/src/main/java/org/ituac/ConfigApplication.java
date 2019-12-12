package org.ituac;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ituac
 */


@SpringBootApplication
public class ConfigApplication {

    public static void main(String[] args){
            SpringApplication.run(ConfigApplication.class, args);
    }

    @Slf4j
    @RestController
    @RefreshScope
    static class TestController {

        @Value("${didispace.title:}")
        private String title;

        @GetMapping("/test")
        public String hello() {
            if(title.equals("")){
                return "没有获取到，空数据";
            }
            return title;
        }

    }


    /**
     * 多文件加载的例子使用的验证接口
     *
     * blog: http://blog.didispace.com/spring-cloud-alibaba-nacos-config-3/
     */
    @Slf4j
    @RestController
    @RefreshScope
    static class Test2Controller {

        @Value("${didispace.title:}")
        private String title;
        @Value("${aaa:}")
        private String aaa;
        @Value("${bbb:}")
        private String bbb;

        @GetMapping("/test2")
        public String test2() {
            return title + ", " + aaa + ", " + bbb;
        }

    }

}
