package com.example.mailaccountscanner.domain.id;

import com.example.mailaccountscanner.domain.MailAccount;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class MailId implements Serializable {

    @ManyToOne(cascade = CascadeType.REMOVE,optional = false)
    private MailAccount mailAccount;

    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public MailId() {
    }

    public MailId(MailAccount mailAccount) {
        this.mailAccount = mailAccount;
    }

    public MailAccount getMailAccount() {
        return mailAccount;
    }

    public void setMailAccount(MailAccount mailAccount) {
        this.mailAccount = mailAccount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
