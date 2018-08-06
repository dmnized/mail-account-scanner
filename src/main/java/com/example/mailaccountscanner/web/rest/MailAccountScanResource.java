package com.example.mailaccountscanner.web.rest;

import com.example.mailaccountscanner.service.MailAccountScanService;
import com.example.mailaccountscanner.service.MailAccountService;
import com.example.mailaccountscanner.service.dto.MailAccountScanDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/api")
public class MailAccountScanResource {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final MailAccountScanService mailAccountScanService;

    private final MailAccountService mailAccountService;

    public MailAccountScanResource(MailAccountScanService mailAccountScanService, MailAccountService mailAccountService) {
        this.mailAccountScanService = mailAccountScanService;
        this.mailAccountService = mailAccountService;
    }

    @PostMapping("/mail-account-scans")
    public ResponseEntity<MailAccountScanDTO> scanMailAccount(@Valid @RequestBody MailAccountScanDTO mailAccountScanDTO) throws Exception {
        log.debug("REST request to run MailAccountScan : {}", mailAccountScanDTO);

        if( ! mailAccountService.existsById(mailAccountScanDTO.getMailAccountId()))
            return ResponseEntity.notFound().build();

        if(mailAccountScanService.isMailAccountScanAlreadyRunning(mailAccountScanDTO))
            return ResponseEntity.status(HttpStatus.CONFLICT).body(mailAccountScanDTO);

        mailAccountScanDTO = mailAccountScanService.runMailAccountScan(mailAccountScanDTO);

        return ResponseEntity.created(linkTo(methodOn(MailAccountScanResource.class)
                .getMailAccountScan(mailAccountScanDTO))
                .toUri())
                .build();

    }

    @GetMapping("/mail-account-scans")
    public ResponseEntity<MailAccountScanDTO> getMailAccountScan(@Valid MailAccountScanDTO mailAccountScanDTO) throws Exception{
        log.debug("REST request to get MailAccountScan : {}", mailAccountScanDTO);

        if(mailAccountScanService.isMailAccountScanAlreadyRunning(mailAccountScanDTO))
            return ResponseEntity.ok().body(mailAccountScanDTO);

        return ResponseEntity.notFound().build();
    }


}
