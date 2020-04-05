package com.zby.manage.quartz.job;

import lombok.extern.log4j.Log4j;
import org.quartz.*;

/**
 * Description:
 * User: 小卡掌柜
 * Date: 2020-04-04
 * Time: 7:51 PM
 */
//表示quartz不要并发地执行同一个job定义的多个实例
//@DisallowConcurrentExecution
//表示在成功执行方法后，更新jobdetail中map数据
//@PersistJobDataAfterExecution
@Log4j
public class FirstQuertzJob implements Job{
    public FirstQuertzJob(){

    }
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobKey jobKey = jobExecutionContext.getJobDetail().getKey();
        log.info("jobkey:"+jobKey);
        Object param = jobExecutionContext.getJobDetail().getJobDataMap().get("parma");
        log.info("this is first job"+param.toString());
    }
}
