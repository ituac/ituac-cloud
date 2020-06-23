package org.ituac.jobs.config;

import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.event.JobEventConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.ituac.jobs.OrderSimpleJob;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.annotation.Resource;

@Configuration
public class OrderJobConfig {

    @Resource
    private ZookeeperRegistryCenter regCenter;

    @Resource
    private JobEventConfiguration jobEventConfiguration;


    @Bean
    public SimpleJob simpleJob() {
        return new OrderSimpleJob();
    }

    private LiteJobConfiguration getLiteJobConfiguration(final Class<? extends SimpleJob> jobClass, final String cron,
                                                         final int shardingTotalCount, final String shardingItemParameters) {
        return LiteJobConfiguration.newBuilder(
                new SimpleJobConfiguration(JobCoreConfiguration.newBuilder(
                        jobClass.getName(), cron, shardingTotalCount).shardingItemParameters(shardingItemParameters).build(),
                        jobClass.getCanonicalName()))
                .overwrite(true).build();
    }

    @Bean(initMethod = "init")
    public JobScheduler simpleJobScheduler(final SimpleJob simpleJob, @Value("${elasticjob.orderSimpleJob.cron}") final String cron,

                                           @Value("${elasticjob.orderSimpleJob.shardingTotalCount}") final int shardingTotalCount,
                                           @Value("${elasticjob.orderSimpleJob.shardingItemParameters}") final String shardingItemParameters) {
        //simpleJob：简单作业
        return new SpringJobScheduler(simpleJob, regCenter, getLiteJobConfiguration(simpleJob.getClass(),
                cron, shardingTotalCount, shardingItemParameters), jobEventConfiguration);
    }




}
