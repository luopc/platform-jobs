package com.luopc.platform.web.job;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class SpringJobExecutor {


    /**
     * 1、简单任务示例（Bean模式）
     */
    @XxlJob("demoJobHandler")
    public void demoJobHandler() throws Exception {
        XxlJobHelper.log("XXL-JOB, Hello World.");

        String jobParm = XxlJobHelper.getJobParam();
        log.info("jobParm:{}", jobParm);
        for (int i = 0; i < 5; i++) {
            XxlJobHelper.log("beat at:" + i);
            TimeUnit.SECONDS.sleep(2);
        }
        // default success
    }

    /**
     * 2、分片广播任务
     */
    @XxlJob("shardingJobHandler")
    public void shardingJobHandler() throws Exception {

        // 分片参数
        int shardIndex = XxlJobHelper.getShardIndex();
        int shardTotal = XxlJobHelper.getShardTotal();

        XxlJobHelper.log("分片参数：当前分片序号 = {}, 总分片数 = {}", shardIndex, shardTotal);
        log.info("分片参数：当前分片序号 = {}, 总分片数 = {}", shardIndex, shardTotal);
        // 业务逻辑
        for (int i = 0; i < shardTotal; i++) {
            if (i == shardIndex) {
                log.info("第 {} 片, 命中分片开始处理", i);
                XxlJobHelper.log("第 {} 片, 命中分片开始处理", i);
            } else {
                log.info("第 {} 片, 忽略", i);
                XxlJobHelper.log("第 {} 片, 忽略", i);
            }
        }

    }

}
