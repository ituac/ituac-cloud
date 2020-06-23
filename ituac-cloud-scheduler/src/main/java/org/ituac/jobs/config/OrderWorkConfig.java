package org.ituac.jobs.config;

import com.dangdang.ddframe.job.api.dataflow.DataflowJob;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.dataflow.DataflowJobConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.event.JobEventConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.ituac.jobs.OrderSimpleJob;
import org.ituac.jobs.OrderWorkflowJob;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.annotation.Resource;

@Configuration
public class OrderWorkConfig {

    @Resource
    private ZookeeperRegistryCenter regCenter;

    @Resource
    private JobEventConfiguration jobEventConfiguration;


    @Bean
    public DataflowJob dataflow() {
        return new OrderWorkflowJob();
    }

    private LiteJobConfiguration getLiteJobConfiguration(final Class<? extends DataflowJob> jobClass, final String cron,
                                                         final int shardingTotalCount, final String shardingItemParameters) {
        // LiteJobConfiguration 实现了JobRootConfiguration 作业配置
        return LiteJobConfiguration.newBuilder(

                //JobCoreConfiguration核心作业配置
                new DataflowJobConfiguration(JobCoreConfiguration.newBuilder(
                        jobClass.getName(), cron, shardingTotalCount).shardingItemParameters(shardingItemParameters).build(),
                        jobClass.getCanonicalName(),true))
                .overwrite(true).build();
    }

    @Bean(initMethod = "init")
    public JobScheduler dataflowJobScheduler(final DataflowJob dataflow, @Value("${elasticjob.orderDataflowJob.cron}") final String cron,
        //JobScheduler 作业调度器
                                           @Value("${elasticjob.orderDataflowJob.shardingTotalCount}") final int shardingTotalCount,
                                           @Value("${elasticjob.orderDataflowJob.shardingItemParameters}") final String shardingItemParameters) {
        System.out.println("===========" + dataflow.getClass().getName());
        return new SpringJobScheduler(dataflow, regCenter, getLiteJobConfiguration(dataflow.getClass(),
                cron, shardingTotalCount, shardingItemParameters), jobEventConfiguration);
    }




}
