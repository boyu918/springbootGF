package com.zby.manage.quartz;

import com.zby.manage.quartz.job.FirstQuertzJob;
import com.zby.manage.quartz.listen.FirstJobListener;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.quartz.impl.matchers.KeyMatcher;
import org.quartz.impl.triggers.CoreTrigger;

/**
 * Description:
 * User: 小卡掌柜
 * Date: 2020-04-04
 * Time: 7:54 PM
 */
public class SimpleQuertzTest {
    public static void main(String[] args) {
        try {
            //jobdetail 带着job
            JobDetail jobDetail  = JobBuilder.newJob(FirstQuertzJob.class).withIdentity("firstJob","group1")
                    .usingJobData("parma","12345")
                    .usingJobData("parma2","55555").build();
            //trigger 带着定时器规则
            Trigger simpleTrigger = TriggerBuilder.newTrigger().withIdentity("myTrigger","group1")
                    .startNow()
                    //增加优先级
//                  .withPriority(Trigger.MISFIRE_INSTRUCTION_IGNORE_MISFIRE_POLICY)
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever())
                    .build();
            Trigger coreTrigger = TriggerBuilder.newTrigger().withIdentity("coreTrigger","gourp2")
                    .startNow()
                    .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
                    .build();
             //simpleTrigger 和 cornTrigger
            //初始化调度器
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            //joblisteng
            scheduler.getListenerManager().addJobListener(new FirstJobListener(), KeyMatcher.keyEquals(jobDetail.getKey()));
            //将jobdetail和trigger结合放入调度器中
            scheduler.scheduleJob(jobDetail,simpleTrigger);
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }
}
