package com.bellintegrator.bankaccount.repository;

import com.bellintegrator.bankaccount.models.Account;
import com.bellintegrator.bankaccount.models.Card;
import com.bellintegrator.bankaccount.models.dto.AccountDTO;
import com.bellintegrator.bankaccount.models.dto.CardDTO;
import com.bellintegrator.bankaccount.models.dto.ClientDTO;
import com.bellintegrator.bankaccount.models.dto.mapper.AccountMapper;
import com.bellintegrator.bankaccount.models.enums.CardStatus;
import com.bellintegrator.bankaccount.repository.interfaces.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

    private final AccountMapper accountMapper;
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    @Transactional
    public void updateAccountBalance(Long accountId) {
        Optional<Account> optionalAccount = accountRepository.findById(accountId);

        if (optionalAccount.isEmpty()) {
            logger.error("Account with ID: {} not found", accountId);
            return;
        }

        Account account = optionalAccount.get();
        List<Card> cards = account.getCards();
        BigDecimal cardBalance = BigDecimal.ZERO;

        if (cards != null && !cards.isEmpty()) {
            for (Card card : cards) {
                if (card.getCardStatus() == CardStatus.ACTIVE && card.getCardBalance() != null) {
                    cardBalance = cardBalance.add(card.getCardBalance());
                }
            }
        }
        account.setAccountBalance(cardBalance);
        accountRepository.save(account);
        logger.info("Updated balance for account with ID: {}", accountId);
    }

    @Transactional(readOnly = true)
    public List<AccountDTO> getAllAccounts() {
        return accountMapper.toAccountDTOList(accountRepository.findAll());
    }

    @Transactional
    public AccountDTO createAccount(AccountDTO accountDTO) {
        validateAccount(accountDTO);
        Account account = accountMapper.accountDTOToAccount(accountDTO);
        accountRepository.save(account);
        return accountMapper.accountToAccountDTO(account);
    }

    @Transactional(readOnly = true)
    public boolean existsByAccountNumber(String accountNumber) {
        boolean isNumberExists = accountRepository.existsByAccountNumber(accountNumber);
        logger.info("Account with number {} exists: {}", accountNumber, isNumberExists);
        return isNumberExists;
    }

    public AccountDTO getAccountByCard(CardDTO cardDTO) {
        Account account = accountRepository.findByCardId(cardDTO.getId());
        if (account != null) {
            return accountMapper.accountToAccountDTO(account);
        }
        return null;
    }

    @Transactional(readOnly = true)
    public List<AccountDTO> getAllAccountsByClientId(Long clientId) {
        List<Account> accounts = accountRepository.findAllByClientId(clientId);
        List<AccountDTO> accountsDTO = accountMapper.toAccountDTOList(accounts);
        return accountsDTO;
    }

    private void validateAccount(AccountDTO accountDTO) {
        if (accountDTO.getAccountType() == null) {
            throw new IllegalArgumentException("Account type cannot be null");
        }
    }

}
