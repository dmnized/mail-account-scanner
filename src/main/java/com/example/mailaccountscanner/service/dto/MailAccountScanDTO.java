package com.example.mailaccountscanner.service.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.Instant;

public class MailAccountScanDTO {

    @NotNull
    private Long mailAccountId;

    @NotNull
    @DateTimeFormat(pattern = "yyyyMMdd")
    private Instant receiptDate;

    public MailAccountScanDTO() {
    }

    public MailAccountScanDTO(Long mailAccountId, Instant receiptDate) {
        this.mailAccountId = mailAccountId;
        this.receiptDate = receiptDate;
    }

    public Long getMailAccountId() {
        return mailAccountId;
    }

    public void setMailAccountId(Long mailAccountId) {
        this.mailAccountId = mailAccountId;
    }

    public Instant getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(Instant receiptDate) {
        this.receiptDate = receiptDate;
    }

    @Override
    public String toString() {
        return "MailAccountScanDTO{" +
                "mailAccountId=" + mailAccountId +
                ", receiptDate=" + receiptDate +
                '}';
    }
}
