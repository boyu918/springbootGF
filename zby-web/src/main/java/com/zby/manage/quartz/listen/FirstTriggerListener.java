package com.zby.manage.quartz.listen;

import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.TriggerListener;

/**
 * Description:
 * User: 小卡掌柜
 * Date: 2020-04-05
 * Time: 3:43 PM
 */
public class FirstTriggerListener implements TriggerListener{
    @Override
    public String getName() {
        return "firstTriggerlisten";
    }

    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext context) {
        System.out.println("firstTriggerlisten triggerFired");
    }

    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
        return false;
    }

    @Override
    public void triggerMisfired(Trigger trigger) {
        System.out.println("firstTriggerlisten triggerMisfired");
    }

    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext context, Trigger.CompletedExecutionInstruction triggerInstructionCode) {
        System.out.println("firstTriggerlisten triggerComplete");
    }
}
