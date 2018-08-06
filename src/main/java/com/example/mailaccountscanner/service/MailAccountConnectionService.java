package com.example.mailaccountscanner.service;

import com.example.mailaccountscanner.service.dto.MailAccountDTO;

import javax.mail.*;
import javax.mail.search.SearchTerm;
import java.util.Arrays;
import java.util.List;

public abstract class MailAccountConnectionService{

    protected Session session;

    protected Store store;

    protected Folder folder;

    public abstract void connectToMailAccount(MailAccountDTO mailAccountDTO) throws MessagingException;

    public List<Message> getMessagesByTerm(SearchTerm searchTerm) throws MessagingException {
       this.folder.open(Folder.READ_WRITE);
       return Arrays.asList(this.folder.search(searchTerm));
    }

}
