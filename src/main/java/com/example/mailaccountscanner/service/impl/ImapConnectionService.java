package com.example.mailaccountscanner.service.impl;

import com.example.mailaccountscanner.service.MailAccountConnectionService;
import com.example.mailaccountscanner.service.dto.MailAccountDTO;

import javax.mail.*;
import javax.mail.search.SearchTerm;
import java.util.List;
import java.util.Properties;

public class ImapConnectionService extends MailAccountConnectionService {

    private String protocol = "imaps";

    private String file = "INBOX";

    @Override
    public void connectToMailAccount(MailAccountDTO mailAccountDTO) throws MessagingException {
        URLName url = new URLName(protocol,
                mailAccountDTO.getHost(),
                mailAccountDTO.getPort(),
                file,
                mailAccountDTO.getEmailAddress(),
                mailAccountDTO.getPassword());

        Properties props = null;
        try {
            props = System.getProperties();
            props.put("mail.imaps.ssl.checkserveridentity", "false");
            props.put("mail.imaps.ssl.trust", "*");
        } catch (SecurityException sex) {
            props = new Properties();
        }
        this.session = Session.getInstance(props, null);

        this.store = session.getStore(url);
        this.store.connect();

        this.folder = this.store.getFolder(url);

    }


}
