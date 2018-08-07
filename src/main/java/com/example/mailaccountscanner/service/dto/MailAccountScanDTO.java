package com.example.mailaccountscanner.service.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static com.example.mailaccountscanner.config.Constants.RECEIPT_DAY_VALIDATION_PATTERN;

public class MailAccountScanDTO {

    @NotNull
    private Long mailAccountId;

    @NotNull
    @Pattern(regexp = RECEIPT_DAY_VALIDATION_PATTERN)
    private String receiptDay;

    public MailAccountScanDTO() {
    }

    public MailAccountScanDTO(Long mailAccountId, String receiptDay) {
        this.mailAccountId = mailAccountId;
        this.receiptDay = receiptDay;
    }

    public Long getMailAccountId() {
        return mailAccountId;
    }

    public void setMailAccountId(Long mailAccountId) {
        this.mailAccountId = mailAccountId;
    }

    public String getReceiptDay() {
        return receiptDay;
    }

    public void setReceiptDay(String receiptDay) {
        this.receiptDay = receiptDay;
    }

    @Override
    public String toString() {
        return "MailAccountScanDTO{" +
                "mailAccountId=" + mailAccountId +
                ", receiptDay='" + receiptDay + '\'' +
                '}';
    }
}
