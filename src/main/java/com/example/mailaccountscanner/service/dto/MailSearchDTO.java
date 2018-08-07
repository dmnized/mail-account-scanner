package com.example.mailaccountscanner.service.dto;

import com.example.mailaccountscanner.domain.enumeration.MailType;

import javax.validation.constraints.Pattern;

import static com.example.mailaccountscanner.config.Constants.RECEIPT_DAY_VALIDATION_PATTERN;

public class MailSearchDTO {

    private Long mailAccountId;

    private MailType mailType;

    @Pattern(regexp = RECEIPT_DAY_VALIDATION_PATTERN)
    private String receiptDay;

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

    public String getReceiptDay() {
        return receiptDay;
    }

    public void setReceiptDay(String receiptDay) {
        this.receiptDay = receiptDay;
    }

    @Override
    public String toString() {
        return "MailSearchDTO{" +
                "mailAccountId=" + mailAccountId +
                ", mailType=" + mailType +
                ", receiptDay='" + receiptDay + '\'' +
                '}';
    }
}
