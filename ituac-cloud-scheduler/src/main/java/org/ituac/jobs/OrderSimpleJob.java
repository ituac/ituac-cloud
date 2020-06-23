package org.ituac.jobs;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class OrderSimpleJob implements SimpleJob {

    Logger logger = LoggerFactory.getLogger(OrderSimpleJob.class);

    @Override
    public void execute(ShardingContext shardingContext) {
        //任务分片
        switch (shardingContext.getShardingItem()) {
            case 0:
                logger.info("job名称={},分片数量={},当前分片={},当前分片名称={},当前自定义参数={} -----------", shardingContext.getJobName(), shardingContext.getShardingTotalCount(), shardingContext.getShardingItem(), shardingContext.getShardingParameter(), shardingContext.getJobParameter());
                break;
            case 1:
                logger.info("job名称={},分片数量={},当前分片={},当前分片名称={},当前自定义参数={} -----------", shardingContext.getJobName(), shardingContext.getShardingTotalCount(), shardingContext.getShardingItem(), shardingContext.getShardingParameter(), shardingContext.getJobParameter());
                break;
            case 2:
                logger.info("job名称={},分片数量={},当前分片={},当前分片名称={},当前自定义参数={} -----------", shardingContext.getJobName(), shardingContext.getShardingTotalCount(), shardingContext.getShardingItem(), shardingContext.getShardingParameter(), shardingContext.getJobParameter());
                break;
        }


        //拿到指定的数据在做业务处理
        if(shardingContext.getJobParameter().equals("Beijing")){
            result();
        }

    }

    public void result(){
        //业务处理
        System.out.println("======业务处理======");
    }

}
