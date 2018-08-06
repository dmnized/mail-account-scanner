package com.example.mailaccountscanner.service;

import com.example.mailaccountscanner.service.dto.MailAccountScanDTO;

public interface MailAccountScanService {

    MailAccountScanDTO runMailAccountScan(MailAccountScanDTO mailAccountScanDTO) throws Exception;

    boolean isMailAccountScanAlreadyRunning(MailAccountScanDTO mailAccountScanDTO) throws Exception;

}
