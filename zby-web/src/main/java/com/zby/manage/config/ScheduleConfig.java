package com.zby.manage.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * Description:
 * User: 小卡掌柜
 * Date: 2020-04-05
 * Time: 6:01 PM
 */
@Configuration
public class ScheduleConfig {
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(@Qualifier("dataSource") DataSource dataSource) throws IOException {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
//        factory.setDataSource(dataSource);


        // quartz参数
        factory.setQuartzProperties(quartzProperties());

        factory.setSchedulerName("MyScheduler");
        // 延时启动
        factory.setStartupDelay(1);
        factory.setApplicationContextSchedulerContextKey("applicationContextKey");
        // 可选，QuartzScheduler
        // 启动时更新己存在的Job，这样就不用每次修改targetObject后删除qrtz_job_details表对应记录了
        factory.setOverwriteExistingJobs(true);
        // 设置自动启动，默认为true
        factory.setAutoStartup(true);


		/*
		CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
		cronTriggerFactoryBean.setJobDetail(
				JobBuilder.newJob(ScheduleJob.class).build());
		cronTriggerFactoryBean.setStartDelay(3000);
		cronTriggerFactoryBean.setCronExpression("0/10 * * * * ?");
		// 通过这个设置 在项目启动时启动
		factory.setTriggers(cronTriggerFactoryBean.getObject());
		*/

        return factory;
    }

    /**
     * 加载Quartz配置
     *
     * @return
     * @throws IOException
     */
    @Bean
    public Properties quartzProperties() throws IOException {
        //使用Spring的PropertiesFactoryBean对属性配置文件进行管理
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        //注意：quartz的配置文件从指定系统目录中获取，而不是从classpath中获取
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        //propertiesFactoryBean.setLocation(new FileSystemResource(propertiesPath));
        //重要：保证其初始化
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }
}
