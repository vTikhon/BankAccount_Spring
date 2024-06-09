package com.bellintegrator.bankaccount.models.listeners;

import com.bellintegrator.bankaccount.models.Account;
import com.bellintegrator.bankaccount.models.generators.NumberGenerator;
import com.bellintegrator.bankaccount.repository.AccountService;
import jakarta.persistence.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class AccountListener implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        AccountListener.applicationContext = applicationContext;
    }

    @PrePersist
    @PreUpdate
    public void generateAccountNumber(Account account) {
        if (account.getAccountNumber() == null || account.getAccountNumber().isEmpty()) {
            AccountService accountService = applicationContext.getBean(AccountService.class);
            String accountNumber;
            do {
                accountNumber = NumberGenerator.generate();
            } while (accountService.existsByAccountNumber(accountNumber));
            account.setAccountNumber(accountNumber);
        }
    }

}

