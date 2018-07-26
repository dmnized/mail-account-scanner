package com.example.mailaccountscanner.service;

import com.example.mailaccountscanner.service.dto.MailDto;

public interface MailService {

    MailDto save(MailDto mailDto);

    MailDto saveToFileSystem(MailDto mailDto);

}
