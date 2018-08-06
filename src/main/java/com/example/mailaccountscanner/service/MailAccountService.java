package com.example.mailaccountscanner.service;

import com.example.mailaccountscanner.service.dto.MailAccountDTO;

import java.util.Optional;

public interface MailAccountService {

    Optional<MailAccountDTO> findOne(Long id);

    boolean existsById(Long id);


}
