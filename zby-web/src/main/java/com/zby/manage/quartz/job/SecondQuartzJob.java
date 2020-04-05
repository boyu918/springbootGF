package com.zby.manage.quartz.job;

import lombok.extern.log4j.Log4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

/**
 * Description:
 * User: 小卡掌柜
 * Date: 2020-04-05
 * Time: 6:23 PM
 */
@Log4j
public class SecondQuartzJob implements Job {
    public SecondQuartzJob(){

    }
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobKey jobKey = jobExecutionContext.getJobDetail().getKey();
        log.info("jobkey:"+jobKey);
        Object param = jobExecutionContext.getJobDetail().getJobDataMap().get("parma2");
        log.info("this is second job"+param.toString());
    }
}