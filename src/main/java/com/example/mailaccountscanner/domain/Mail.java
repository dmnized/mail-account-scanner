package com.example.mailaccountscanner.domain;

import com.example.mailaccountscanner.domain.enumeration.MailType;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "mail",indexes = {@Index(name = "HASH_HEX_INDEX",columnList = "hash_hex")})
public class Mail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "mail_account_id")
    private MailAccount mailAccount;

    @Column(name = "receipt_date", nullable = false)
    private Instant receiptDate;

    @Column(name = "sender", nullable = false)
    private String sender;

    @Column(name = "subject", nullable = false)
    private String subject;

    @Enumerated(EnumType.STRING)
    @Column(name = "mail_type")
    private MailType mailType;

    @Column(name = "message_id_header")
    private String messageIdHeader;

    @Column(name = "x_ref_message_id_header")
    private String xRefMessageIdHeader;

    @Column(name = "hash_hex",nullable = false,length = 64,unique = true)
    private String hashHex;

    @Column(name = "full_file_path",nullable = false,unique = true)
    private String fullFilePath;

    @Column(name = "receipt_day",nullable = false,length = 8)
    private String receiptDay;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MailAccount getMailAccount() {
        return mailAccount;
    }

    public void setMailAccount(MailAccount mailAccount) {
        this.mailAccount = mailAccount;
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

    public String getReceiptDay() {
        return receiptDay;
    }

    public void setReceiptDay(String receiptDay) {
        this.receiptDay = receiptDay;
    }
}
