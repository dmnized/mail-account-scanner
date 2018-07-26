package com.example.mailaccountscanner.repository;

import com.example.mailaccountscanner.domain.MailAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailAccountRepository extends JpaRepository<MailAccount,Long> {
}
