package com.example.mailaccountscanner.service;

import com.example.mailaccountscanner.service.dto.MailDTO;
import com.example.mailaccountscanner.service.dto.MailSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface MailService {

    MailDTO save(MailDTO mailDTO);

    boolean isMailAlreadyPresent(MailDTO mailDTO);

    Optional<MailDTO> findByMailAccountIdAndMailId(Long mailAccountId, Long mailId);

    Page<MailDTO> searchMails(MailSearchDTO mailSearchDTO, Pageable pageable);

}
