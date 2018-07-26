package com.example.mailaccountscanner.service;

import com.example.mailaccountscanner.service.dto.MailAccountDto;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.search.SearchTerm;
import java.util.List;

public interface MailAccountService {

    Session connectToMailAccount(MailAccountDto mailAccountDto);

    List<Message> getMessagesByTerm(SearchTerm searchTerm);



}
