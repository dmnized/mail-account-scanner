package com.example.mailaccountscanner.service.impl;

import com.example.mailaccountscanner.repository.MailAccountRepository;
import com.example.mailaccountscanner.service.MailAccountService;
import com.example.mailaccountscanner.service.dto.MailAccountDTO;
import com.example.mailaccountscanner.service.mapper.MailAccountMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MailAccountServiceImpl implements MailAccountService {

    private final MailAccountRepository mailAccountRepository;

    private final MailAccountMapper mailAccountMapper;

    public MailAccountServiceImpl(MailAccountRepository mailAccountRepository, MailAccountMapper mailAccountMapper) {
        this.mailAccountRepository = mailAccountRepository;
        this.mailAccountMapper = mailAccountMapper;
    }

    @Override
    public Optional<MailAccountDTO> findOne(Long id) {
        return mailAccountRepository.findById(id).map(mailAccountMapper::toDto);
    }

    @Override
    public boolean existsById(Long id) {
        return mailAccountRepository.existsById(id);
    }
}
