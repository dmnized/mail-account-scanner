package com.example.mailaccountscanner.service.dto;

import com.example.mailaccountscanner.domain.enumeration.MailType;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;

public class MailSearchDTO {

    private Long mailAccountId;

    private MailType mailType;

    @DateTimeFormat(pattern = "yyyyMMdd")
    private Instant receiptDate;

    public Long getMailAccountId() {
        return mailAccountId;
    }

    public void setMailAccountId(Long mailAccountId) {
        this.mailAccountId = mailAccountId;
    }

    public MailType getMailType() {
        return mailType;
    }

    public void setMailType(MailType mailType) {
        this.mailType = mailType;
    }

    public Instant getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(Instant receiptDate) {
        this.receiptDate = receiptDate;
    }
}
