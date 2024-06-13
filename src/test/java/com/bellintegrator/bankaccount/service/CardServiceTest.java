package com.bellintegrator.bankaccount.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;
import com.bellintegrator.bankaccount.model.Account;
import com.bellintegrator.bankaccount.model.Card;
import com.bellintegrator.bankaccount.model.Client;
import com.bellintegrator.bankaccount.model.enums.AccountType;
import com.bellintegrator.bankaccount.model.enums.CardStatus;
import com.bellintegrator.bankaccount.model.enums.PaymentSystem;
import com.bellintegrator.bankaccount.repository.CardRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CardServiceTest {

    @Mock
    private CardRepository cardRepository;

    @InjectMocks
    private CardService cardService;

    @Test
    public void testUpdateCardBalance() {
        // Arrange
        Client client = new Client();
        client.setId(1L);
        client.setFirstName("Tikhon");
        client.setSurname("Vergentev");
        client.setDateOfBirth(LocalDate.of(1988, 2, 24));
        client.setPassport("4000444333");

        Account account = new Account();
        account.setId(1L);
        account.setAccountNumber("0987654321987654321");
        account.setAccountType(AccountType.DEBIT);
        account.setAccountBalance(BigDecimal.valueOf(1000));
        account.setClient(client);

        Card card = new Card();
        card.setId(1L);
        card.setCardNumber("1234567890123456789");
        card.setCardBalance(BigDecimal.valueOf(1000));
        card.setPaymentSystem(PaymentSystem.VISA);
        card.setCardStatus(CardStatus.ACTIVE);
        card.setAccount(account);

        account.setCards(Collections.singletonList(card));
        client.setAccounts(Collections.singletonList(account));

        // Настройка поведения мока для метода findById
        when(cardRepository.findById(1L)).thenReturn(Optional.of(card));

        // Act
        cardService.updateCardBalance(1L, BigDecimal.valueOf(5678));

        // Assert
        assertEquals(BigDecimal.valueOf(5678), card.getCardBalance());
    }
}
