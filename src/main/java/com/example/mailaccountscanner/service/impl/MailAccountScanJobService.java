package com.example.mailaccountscanner.service.impl;

import com.example.mailaccountscanner.job.JobProcess;
import com.example.mailaccountscanner.service.JobService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("mailAccountScanJobService")
public class MailAccountScanJobService extends JobService {

    public MailAccountScanJobService(@Qualifier("mailAccountScanAndMessagesStoreProcess") JobProcess process) {
        super(process);
    }
}
