package com.example.mailaccountscanner.web.rest;

import com.example.mailaccountscanner.MailAccountScannerApplication;
import com.example.mailaccountscanner.service.MailAccountScanService;
import com.example.mailaccountscanner.service.MailAccountService;
import com.example.mailaccountscanner.service.dto.MailAccountScanDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.Instant;

import static com.example.mailaccountscanner.utils.Test.convertObjectToJsonBytes;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MailAccountScannerApplication.class)
public class MailAccountScanResourceIntTest {

    private MockMvc restResourceMockMvc;

    private MailAccountScanDTO mailAccountScanDTO;

    @Autowired
    private MailAccountScanService mailAccountScanService;

    @Autowired
    private MailAccountService mailAccountService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MailAccountScanResource resourceResource = new MailAccountScanResource(mailAccountScanService, mailAccountService);
        this.restResourceMockMvc = MockMvcBuilders.standaloneSetup(resourceResource).build();
    }

    @Test
    public void mailAccountIdIsRequired() throws Exception {
        mailAccountScanDTO = new MailAccountScanDTO(null,Instant.now());

        this.restResourceMockMvc.perform(post("/api/mail-account-scans")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(convertObjectToJsonBytes(mailAccountScanDTO)))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void receiptDatedateIsRequired() throws Exception {
        mailAccountScanDTO = new MailAccountScanDTO(1l,null);

        this.restResourceMockMvc.perform(post("/api/mail-account-scans")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(convertObjectToJsonBytes(mailAccountScanDTO)))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void createdIfRequestValidAndMailScanIsNotRunning() throws Exception {
        mailAccountScanDTO = new MailAccountScanDTO(1l,Instant.now());

        MailAccountScanService mailAccountScanService = new MailAccountScanService() {
            @Override
            public MailAccountScanDTO runMailAccountScan(MailAccountScanDTO mailAccountScanDTO) {
                return mailAccountScanDTO;
            }

            @Override
            public boolean isMailAccountScanAlreadyRunning(MailAccountScanDTO mailAccountScanDTO) {
                return false;
            }
        };

        final MailAccountScanResource resourceResource = new MailAccountScanResource(mailAccountScanService, mailAccountService);
        this.restResourceMockMvc = MockMvcBuilders.standaloneSetup(resourceResource).build();

        try {
            this.restResourceMockMvc.perform(post("/api/mail-account-scans")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(convertObjectToJsonBytes(mailAccountScanDTO)))
                    .andExpect(status().isCreated());
        } catch (Exception e) {
            throw e;
        } finally {
            setup();
        }
    }

    @Test
    public void conflictIfMailScanIsAlreadyRunning() throws Exception {
        mailAccountScanDTO = new MailAccountScanDTO(1l,Instant.now());

        MailAccountScanService mailAccountScanService = new MailAccountScanService() {
            @Override
            public MailAccountScanDTO runMailAccountScan(MailAccountScanDTO mailAccountScanDTO) {
                return null;
            }

            @Override
            public boolean isMailAccountScanAlreadyRunning(MailAccountScanDTO mailAccountScanDTO) {
                return true;
            }
        };

        final MailAccountScanResource resourceResource = new MailAccountScanResource(mailAccountScanService, mailAccountService);
        this.restResourceMockMvc = MockMvcBuilders.standaloneSetup(resourceResource).build();

        try {
            this.restResourceMockMvc.perform(post("/api/mail-account-scans")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(convertObjectToJsonBytes(mailAccountScanDTO)))
                    .andExpect(status().is(HttpStatus.CONFLICT.value()));
        } catch (Exception e) {
            throw e;
        } finally {
            setup();
        }

    }

}
