package com.example.mailaccountscanner.web.rest;

import com.example.mailaccountscanner.domain.Mail;
import com.example.mailaccountscanner.service.MailService;
import com.example.mailaccountscanner.service.dto.MailDTO;
import com.example.mailaccountscanner.service.dto.MailSearchDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MailResource {

    private final Logger log = LoggerFactory.getLogger(MailAccountScanResource.class);

    private MailService mailService;

    public MailResource(MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping("/mails/{mailAccountId}/{mailId}")
    public ResponseEntity<MailDTO> getMail(@PathVariable Long mailAccountId, @PathVariable Long mailId){
        log.debug("REST request to get MailDTO : {} {}", mailAccountId,mailId);

        Optional<MailDTO> mailDTO = mailService.findByMailAccountIdAndMailId(mailAccountId,mailId);
        if( ! mailDTO.isPresent())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(mailDTO.get());
    }

    @GetMapping("/mails")
    public ResponseEntity<List<MailDTO>> searchMails(MailSearchDTO mailSearchDTO, Pageable pageable){
        log.debug("REST request to get MailDTO : {}", mailSearchDTO);
        Page<MailDTO> mailDTOPage = mailService.searchMails(mailSearchDTO,pageable);
        return new ResponseEntity<>(mailDTOPage.getContent(), HttpStatus.OK);
    }


}
