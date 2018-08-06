package com.example.mailaccountscanner.repository;

import com.example.mailaccountscanner.domain.Mail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MailRepository extends JpaRepository<Mail, Long> {

    boolean existsByHashHex(String hashHex);

    Optional<Mail> findByMailAccountIdAndId(Long mailAccountId, Long id);
}
