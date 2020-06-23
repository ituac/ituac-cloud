package org.ituac.jobs;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;

public class OrderWorkflowJob implements DataflowJob {

    Logger logger = LoggerFactory.getLogger(OrderWorkflowJob.class);

    static int j = 0;
    static List list = new ArrayList<String>();

    static {
        for (int i = 0; i < 10; i++) {
            list.add("数据" + i);
        }
    }


    @Override
    public List fetchData(ShardingContext shardingContext) {
        //任务分片
        switch (shardingContext.getShardingItem()) {
            case 0:
                logger.info("fetch-job名称={},分片数量={},当前分片={},当前分片名称={},当前自定义参数={} -----------", shardingContext.getJobName(), shardingContext.getShardingTotalCount(), shardingContext.getShardingItem(), shardingContext.getShardingParameter(), shardingContext.getJobParameter());
                break;
            case 1:
                logger.info("fetch-job名称={},分片数量={},当前分片={},当前分片名称={},当前自定义参数={} -----------", shardingContext.getJobName(), shardingContext.getShardingTotalCount(), shardingContext.getShardingItem(), shardingContext.getShardingParameter(), shardingContext.getJobParameter());
                break;
            case 2:
                logger.info("fetch-job名称={},分片数量={},当前分片={},当前分片名称={},当前自定义参数={} -----------", shardingContext.getJobName(), shardingContext.getShardingTotalCount(), shardingContext.getShardingItem(), shardingContext.getShardingParameter(), shardingContext.getJobParameter());
                break;
        }
        // 获取满足要求的分片数据
        return this.list;
    }

    @Override
    public void processData(ShardingContext shardingContext, List list) {
        // 处理满足要求的分片数据
        logger.info("process-job名称={},分片数量={},当前分片={},当前分片名称={},当前自定义参数={} -----------", shardingContext.getJobName(), shardingContext.getShardingTotalCount(), shardingContext.getShardingItem(), shardingContext.getShardingParameter(), shardingContext.getJobParameter());
        j ++;
        if(j == 2){
            this.list = null;
        }

    }
}
