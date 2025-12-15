package com.luopc.platform.infra;

import com.luopc.platform.infra.jobs.config.FrameLessXxlJobConfig;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class XxlJobFramelessApplication {

    public static void main(String[] args) {

        try {
            // start
            FrameLessXxlJobConfig.getInstance().initXxlJobExecutor();

            // Blocks until interrupted
            while (true) {
                try {
                    TimeUnit.HOURS.sleep(1);
                } catch (InterruptedException e) {
                    break;
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            // destroy
            FrameLessXxlJobConfig.getInstance().destroyXxlJobExecutor();
        }

    }

}
