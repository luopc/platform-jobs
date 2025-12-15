package com.luopc.platform.infra.jobs.config;

import com.luopc.platform.infra.jobs.SampleJobTest;
import com.xxl.job.core.executor.impl.XxlJobSimpleExecutor;
import com.xxl.tool.core.PropTool;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Properties;

@Slf4j
public class FrameLessXxlJobConfig {

    @Getter
    private static final FrameLessXxlJobConfig instance = new FrameLessXxlJobConfig();

    private XxlJobSimpleExecutor xxlJobExecutor = null;

    /**
     * init
     */
    public void initXxlJobExecutor() {

        // load executor prop
        Properties xxlJobProp = PropTool.loadProp("xxl-job-executor.properties");

        // init executor
        xxlJobExecutor = new XxlJobSimpleExecutor();
        xxlJobExecutor.setAdminAddresses(xxlJobProp.getProperty("xxl.job.admin.addresses"));
        xxlJobExecutor.setAccessToken(xxlJobProp.getProperty("xxl.job.admin.accessToken"));
        xxlJobExecutor.setTimeout(Integer.parseInt(xxlJobProp.getProperty("xxl.job.admin.timeout")));
        xxlJobExecutor.setAppname(xxlJobProp.getProperty("xxl.job.executor.appname"));
        xxlJobExecutor.setAddress(xxlJobProp.getProperty("xxl.job.executor.address"));
        xxlJobExecutor.setIp(xxlJobProp.getProperty("xxl.job.executor.ip"));
        xxlJobExecutor.setPort(Integer.parseInt(xxlJobProp.getProperty("xxl.job.executor.port")));
        xxlJobExecutor.setLogPath(xxlJobProp.getProperty("xxl.job.executor.logpath"));
        xxlJobExecutor.setLogRetentionDays(Integer.parseInt(xxlJobProp.getProperty("xxl.job.executor.logretentiondays")));

        // registry job bean
        xxlJobExecutor.setXxlJobBeanList(List.of(new SampleJobTest()));

        // start executor
        try {
            xxlJobExecutor.start();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * destroy
     */
    public void destroyXxlJobExecutor() {
        if (xxlJobExecutor != null) {
            xxlJobExecutor.destroy();
        }
    }
}
