package com.bellintegrator.bankaccount.models.listeners;

import com.bellintegrator.bankaccount.models.Card;
import com.bellintegrator.bankaccount.models.generators.NumberGenerator;
import com.bellintegrator.bankaccount.repository.AccountService;
import com.bellintegrator.bankaccount.repository.CardService;
import jakarta.persistence.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CardListener implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        CardListener.applicationContext = applicationContext;
    }

    @PostPersist
    @PostUpdate
    @PostRemove
    @Transactional
    public void updateAccountBalance(Card card) {
        AccountService accountService = applicationContext.getBean(AccountService.class);
        CardService cardService = applicationContext.getBean(CardService.class);
        Card cardFromContext = cardService.getCardById(card.getId());
        accountService.updateAccountBalance(cardFromContext.getAccount().getId());
    }

    @PrePersist
    @PreUpdate
    public void generateCardNumber(Card card) {
        if (card.getCardNumber() == null || card.getCardNumber().isEmpty()) {
            CardService cardService = applicationContext.getBean(CardService.class);
            String cardNumber;
            do {
                cardNumber = NumberGenerator.generate();
            } while (cardService.existsByCardNumber(cardNumber));
            card.setCardNumber(cardNumber);
        }
    }

}

