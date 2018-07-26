package com.example.mailaccountscanner.service;

import java.time.Instant;

public interface QuartzSchedulerService {

    void runMailAccountScan(Long mailAccountId,Instant date);

    boolean isMailAccountScanAlreadyRunning(Long mailAccountId, Instant date);

}
