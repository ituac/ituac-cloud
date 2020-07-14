package org.ituac.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ituac
 * 放行参数配置
 */
@Data
@Component
@RefreshScope
@ConfigurationProperties(prefix = "ignore")
public class IgnoreClientConfiguration {

    /**
     * 放行终端配置，网关不校验此处的终端
     */
    private List<String> clients = new ArrayList<>();

}
