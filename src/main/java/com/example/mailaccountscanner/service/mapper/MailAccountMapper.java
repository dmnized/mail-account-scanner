package com.example.mailaccountscanner.service.mapper;

import com.example.mailaccountscanner.domain.MailAccount;
import com.example.mailaccountscanner.service.dto.MailAccountDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface MailAccountMapper {

    MailAccountDTO toDto(MailAccount MailAccount);

    MailAccount toEntity(MailAccountDTO mailAccountDTO);
}
