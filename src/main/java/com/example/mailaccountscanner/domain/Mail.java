package com.example.mailaccountscanner.domain;

import com.example.mailaccountscanner.domain.enumeration.MailType;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "mail")
@Index(name = "HASH_HEX_INDEX" columnList = "")
public class Mail {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Id
    @ManyToOne
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

    @Column(name = "hash_hex",nullable = false,length = 2)
    private String hashHex;


    @Column(name = "full_file_path",nullable = false,unique = true)
    private String fullFilePath;


}
