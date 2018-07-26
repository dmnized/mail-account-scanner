package com.example.mailaccountscanner.service.impl;

import com.example.mailaccountscanner.service.QuartzSchedulerService;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class QuartzSchedulerServiceImpl implements QuartzSchedulerService {


    @Override
    public void scanMailAccountByDate(Long mailAccountId, Instant date) {

    }
}
