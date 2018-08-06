package com.example.mailaccountscanner.service.impl;

import com.example.mailaccountscanner.domain.Mail;
import com.example.mailaccountscanner.repository.MailRepository;
import com.example.mailaccountscanner.service.MailService;
import com.example.mailaccountscanner.service.dto.MailDTO;
import com.example.mailaccountscanner.service.dto.MailSearchDTO;
import com.example.mailaccountscanner.service.mapper.MailMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class MailServiceImpl implements MailService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final MailRepository mailRepository;

    private final MailMapper mailMapper;

    public MailServiceImpl(MailRepository mailRepository, MailMapper mailMapper) {
        this.mailRepository = mailRepository;
        this.mailMapper = mailMapper;
    }

    @Override
    @Transactional
    public MailDTO save(MailDTO mailDTO) {
        Mail mail = mailMapper.toEntity(mailDTO);
        mail = mailRepository.save(mail);
        return mailMapper.toDto(mail);
    }

    @Override
    public boolean isMailAlreadyPresent(MailDTO mailDTO) {
        boolean isMailAlreadyPresent = mailRepository.existsByHashHex(mailDTO.getHashHex());
        log.debug("{} isAlreadyPresent: {}",mailDTO.getHashHex(),isMailAlreadyPresent);
        return isMailAlreadyPresent;
    }

    @Override
    public Optional<MailDTO> findByMailAccountIdAndMailId(Long mailAccountId, Long mailId) {
        return mailRepository.findByMailAccountIdAndId(mailAccountId, mailId)
                .map(mailMapper::toDto);
    }

    @Override
    public Page<MailDTO> searchMails(MailSearchDTO mailSearchDTO, Pageable pageable) {
        Example<Mail> mailExample = Example.of(mailMapper.toEntity(mailSearchDTO));
        return mailRepository.findAll(mailExample,pageable)
                .map(mailMapper::toDto);
    }

}
