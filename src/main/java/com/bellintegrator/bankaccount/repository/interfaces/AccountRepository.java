package com.bellintegrator.bankaccount.repository.interfaces;

import com.bellintegrator.bankaccount.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    boolean existsByAccountNumber(String accountNumber);

    @Query("SELECT a FROM Account a JOIN a.cards c WHERE c.id = :cardId")
    Account findByCardId(@Param("cardId") Long cardId);

    @Query("SELECT a FROM Account a WHERE a.client.id = :clientId")
    List<Account> findAllByClientId(@Param("clientId") Long clientId);
}

