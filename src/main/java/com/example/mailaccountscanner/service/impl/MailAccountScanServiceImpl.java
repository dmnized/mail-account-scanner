package com.example.mailaccountscanner.service.impl;

import com.example.mailaccountscanner.service.JobService;
import com.example.mailaccountscanner.service.MailAccountScanService;
import com.example.mailaccountscanner.service.dto.MailAccountScanDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
public class MailAccountScanServiceImpl implements MailAccountScanService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final JobService jobService;

    public MailAccountScanServiceImpl(@Qualifier("mailAccountScanJobService") JobService jobService) {
        this.jobService = jobService;
    }

    @Override
    public MailAccountScanDTO runMailAccountScan(MailAccountScanDTO mailAccountScanDTO) throws Exception {
        log.debug("Request to run a mail account scan: {}",mailAccountScanDTO);
        jobService.scheduleJobNow(buildMailScanJobName(mailAccountScanDTO), buildJobData(mailAccountScanDTO));
        return mailAccountScanDTO;
    }

    @Override
    public boolean isMailAccountScanAlreadyRunning(MailAccountScanDTO mailAccountScanDTO) throws Exception{
        return jobService.isJobRunnig(this.buildMailScanJobName(mailAccountScanDTO));
    }

    private String buildMailScanJobName(MailAccountScanDTO mailAccountScanDTO){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd").withZone(ZoneOffset.UTC);
        return String.format("%s%d%s","MAIL_ACCOUNT_",mailAccountScanDTO.getMailAccountId(),formatter.format(mailAccountScanDTO.getReceiptDate()));

    }

    private Map<String,Object> buildJobData(MailAccountScanDTO mailAccountScanDTO){
        Map<String,Object> map = new HashMap<>();
        map.put("mailAccountId",mailAccountScanDTO.getMailAccountId());
        map.put("date",mailAccountScanDTO.getReceiptDate());
        return map;
    }
}
