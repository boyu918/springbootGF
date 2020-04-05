package com.zby.manage.quartz.listen;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

/**
 * Description:
 * User: 小卡掌柜
 * Date: 2020-04-05
 * Time: 3:43 PM
 */
public class FirstJobListener implements JobListener{
    @Override
    public String getName() {
        return "firstJobListener";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
            //scheduler 将要被执行时调用
        System.out.println("firstJobListener  to be executed");
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        //在jobdetail即将被执行，但又被triggerlistener否决了时调用这个方法
        System.out.println("firstJobListener  jobExecutionVetoed");
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        //在jobdetail被执行之后调用这个方法
        System.out.println("firstJobListener  jobWasExecuted");
    }
}
