package com.example.mailaccountscanner.repository;

import com.example.mailaccountscanner.domain.Mail;
import com.example.mailaccountscanner.domain.id.MailId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailRepository extends JpaRepository<Mail,MailId> {
}
