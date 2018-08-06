package com.example.mailaccountscanner.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class CustomJob extends QuartzJobBean {

    private JobProcess jobProcess;

    private Long mailAccountId;

    private Instant date;

    public void setJobProcess(JobProcess jobProcess) {
        this.jobProcess = jobProcess;
    }

    public void setMailAccountId(Long mailAccountId) {
        this.mailAccountId = mailAccountId;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    @Override
    public void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            Map<String,Object> dataMap = new HashMap<String,Object>();
            dataMap.put("mailAccountId",mailAccountId);
            dataMap.put("date",date);

            jobProcess.process(dataMap);
        } catch (Exception e) {
            throw new JobExecutionException(e);
        }
    }

}
