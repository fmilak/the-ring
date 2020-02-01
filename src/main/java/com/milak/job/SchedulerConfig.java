package com.milak.job;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchedulerConfig {

    @Bean
    public JobDetail clearUploadsJobDetail() {
        return JobBuilder.newJob(ClearUploadsJob.class).withIdentity("clearUploadsJob").storeDurably().build();
    }

    @Bean
    public Trigger clearUploadsJobTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).repeatForever();

        return TriggerBuilder.newTrigger().forJob(clearUploadsJobDetail()).withIdentity("clearUploadsJobTrigger").withSchedule(scheduleBuilder).build();
    }

}
