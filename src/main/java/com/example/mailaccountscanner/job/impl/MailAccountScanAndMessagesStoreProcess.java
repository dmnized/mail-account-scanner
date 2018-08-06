package com.example.mailaccountscanner.job.impl;

import com.example.mailaccountscanner.job.JobProcess;
import com.example.mailaccountscanner.service.MailAccountConnectionService;
import com.example.mailaccountscanner.service.MailAccountService;
import com.example.mailaccountscanner.service.MessageStoreService;
import com.example.mailaccountscanner.service.dto.MailAccountDTO;
import com.example.mailaccountscanner.service.impl.ImapConnectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.search.ComparisonTerm;
import javax.mail.search.ReceivedDateTerm;
import javax.mail.search.SearchTerm;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@Qualifier("mailAccountScanAndMessagesStoreProcess")
public class MailAccountScanAndMessagesStoreProcess implements JobProcess {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final MailAccountService mailAccountService;

    private final MessageStoreService messageStoreService;

    public MailAccountScanAndMessagesStoreProcess(MailAccountService mailAccountService,
                                                  @Qualifier("FileSystemAndDatabaseMessageStoreServiceImpl")
                                                          MessageStoreService messageStoreService) {
        this.mailAccountService = mailAccountService;
        this.messageStoreService = messageStoreService;
    }

    @Override
    public void process(Map<String, Object> processData) throws Exception {

        Optional<Long> mailAccountId = getMailAccountId(processData);
        Optional<Instant> receiptDate = getReceiptDate(processData);

        if (processDataArePresent(mailAccountId, receiptDate)) {
            List<Message> messages = getMessagesUsingImap(mailAccountId, receiptDate);
            messageStoreService.saveMessagesForMailAccount(mailAccountId.get(),messages);
        }else{
            log.error("Process data are not present: {} {}",mailAccountId, receiptDate);
        }

    }

    private List<Message> getMessagesUsingImap(Optional<Long> mailAccountId, Optional<Instant> receiptDate) throws Exception {
        MailAccountConnectionService mailAccountConnectionService = connectToMailAccountUsingImap(mailAccountId);

        return searchMessagesByReceiptDate(receiptDate, mailAccountConnectionService);
    }

    private boolean processDataArePresent(Optional<Long> mailAccountId, Optional<Instant> receiptDate) {
        return mailAccountId.isPresent() && receiptDate.isPresent();
    }

    private List<Message> searchMessagesByReceiptDate(Optional<Instant> receiptDate, MailAccountConnectionService mailAccountConnectionService) throws MessagingException {
        SearchTerm term = new ReceivedDateTerm(ComparisonTerm.EQ, Date.from(receiptDate.get()));
        return mailAccountConnectionService.getMessagesByTerm(term);
    }

    private MailAccountConnectionService connectToMailAccountUsingImap(Optional<Long> mailAccountId) throws Exception {
        MailAccountDTO mailAccountDTO = getMailAccount(mailAccountId);
        MailAccountConnectionService mailAccountConnectionService = new ImapConnectionService();
        mailAccountConnectionService.connectToMailAccount(mailAccountDTO);
        return mailAccountConnectionService;
    }

    private MailAccountDTO getMailAccount(Optional<Long> mailAccountId) throws Exception {
        return mailAccountService.findOne(mailAccountId.get())
                .orElseThrow(() -> new Exception("Mail Account not found"));
    }

    private Optional<Instant> getReceiptDate(Map<String, Object> processData) {
        return Optional.ofNullable(processData)
                .map(m -> m.get("date"))
                .map(Instant.class::cast);
    }

    private Optional<Long> getMailAccountId(Map<String, Object> processData) {
        return Optional.ofNullable(processData)
                .map(m -> m.get("mailAccountId"))
                .map(v -> v.toString())
                .map(Long::valueOf);
    }
}
