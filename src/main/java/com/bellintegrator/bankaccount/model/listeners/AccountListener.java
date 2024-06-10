package com.bellintegrator.bankaccount.model.listeners;

import com.bellintegrator.bankaccount.model.Account;
import com.bellintegrator.bankaccount.model.generators.NumberGenerator;
import com.bellintegrator.bankaccount.service.AccountService;
import jakarta.persistence.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class AccountListener implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) {
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

