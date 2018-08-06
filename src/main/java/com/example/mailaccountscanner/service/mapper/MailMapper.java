package com.example.mailaccountscanner.service.mapper;

import com.example.mailaccountscanner.domain.Mail;
import com.example.mailaccountscanner.domain.MailAccount;
import com.example.mailaccountscanner.service.dto.MailDTO;
import com.example.mailaccountscanner.service.dto.MailSearchDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {})
public interface MailMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "mailAccount.id", target = "mailAccountId")
    MailDTO toDto(Mail mail);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "mailAccountId", target = "mailAccount")
    Mail toEntity(MailDTO mailDTO);

    @Mapping(source = "mailAccountId", target = "mailAccount")
    Mail toEntity(MailSearchDTO mailSearchDTO);

    default MailAccount map(Long mailAccountId){
        MailAccount mailAccount = new MailAccount();
        mailAccount.setId(mailAccountId);
        return mailAccount;
    }

    default Long map(MailAccount mailAccount){
        return mailAccount.getId();
    }


}
