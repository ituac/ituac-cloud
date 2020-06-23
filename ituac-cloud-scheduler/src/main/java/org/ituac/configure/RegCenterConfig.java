package org.ituac.configure;

import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.annotation.Resource;

@Configuration
@Data
@ConditionalOnExpression("'${elasticjob.serverlists}'.length() > 0")
public class RegCenterConfig {


    @Resource
    private HikariDataSource dataSource;

    @Bean(initMethod = "init")
    public ZookeeperRegistryCenter regCenter(@Value("${elasticjob.serverlists}") final String serverList,
                                             @Value("${elasticjob.namespace}") final String namespace) {
        return new ZookeeperRegistryCenter(new ZookeeperConfiguration(serverList, namespace));
    }

}
