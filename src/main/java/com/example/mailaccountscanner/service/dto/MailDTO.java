package com.example.mailaccountscanner.service.dto;

import com.example.mailaccountscanner.domain.enumeration.MailType;

import java.time.Instant;

public class MailDTO {

    private Long id;

    private Long mailAccountId;

    private Instant receiptDate;

    private String sender;

    private String subject;

    private MailType mailType;

    private String messageIdHeader;

    private String xRefMessageIdHeader;

    private String hashHex;

    private String fullFilePath;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public MailType getMailType() {
        return mailType;
    }

    public void setMailType(MailType mailType) {
        this.mailType = mailType;
    }

    public String getMessageIdHeader() {
        return messageIdHeader;
    }

    public void setMessageIdHeader(String messageIdHeader) {
        this.messageIdHeader = messageIdHeader;
    }

    public String getxRefMessageIdHeader() {
        return xRefMessageIdHeader;
    }

    public void setxRefMessageIdHeader(String xRefMessageIdHeader) {
        this.xRefMessageIdHeader = xRefMessageIdHeader;
    }

    public String getHashHex() {
        return hashHex;
    }

    public void setHashHex(String hashHex) {
        this.hashHex = hashHex;
    }

    public String getFullFilePath() {
        return fullFilePath;
    }

    public void setFullFilePath(String fullFilePath) {
        this.fullFilePath = fullFilePath;
    }
}
