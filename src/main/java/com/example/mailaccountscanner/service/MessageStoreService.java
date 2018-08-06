package com.example.mailaccountscanner.service;

import com.example.mailaccountscanner.service.dto.MailDTO;

import javax.mail.Message;
import java.util.List;

public interface MessageStoreService {

    List<MailDTO> saveMessagesForMailAccount(Long mailAccountId, List<Message> messages);
}
