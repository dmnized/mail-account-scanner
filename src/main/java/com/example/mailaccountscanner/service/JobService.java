package com.example.mailaccountscanner.service;

import com.example.mailaccountscanner.job.CustomJob;
import com.example.mailaccountscanner.job.JobProcess;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.Map;

public abstract class JobService {

    protected JobProcess process;

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    public JobService(JobProcess process) {
        this.process = process;
    }

    public void scheduleJobNow(String jobName, Map<String,Object> jobData) throws SchedulerException {

        JobDetail jobDetail = this.buildJobDetail(jobName);

        jobData.put("jobProcess",process);

        Trigger trigger = buildTrigger(jobDetail,jobData);

        schedulerFactoryBean.getScheduler().scheduleJob(jobDetail,trigger);
    }

    protected JobDetail buildJobDetail(String jobName){
        return JobBuilder.newJob()
                .ofType(CustomJob.class)
                .withIdentity(this.buildJobKey(jobName))
                .build();
    }

    protected Trigger buildTrigger(JobDetail jobDetail,Map<String,Object> jobData) {
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(jobDetail.getKey().getName())
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule())
                .build();

        trigger.getJobDataMap().putAll(jobData);

        return trigger;
    }



    public boolean isJobRunnig(String jobName) throws SchedulerException {

        return schedulerFactoryBean.getScheduler()
                .getCurrentlyExecutingJobs()
                .stream()
                .filter(jobExecutionContext ->
                        jobExecutionContext
                                .getJobDetail()
                                .equals(this.buildJobDetail(jobName)))
                .count() > 0;
    }

    public JobKey buildJobKey(String jobName){
        return new JobKey(jobName);
    }


}
