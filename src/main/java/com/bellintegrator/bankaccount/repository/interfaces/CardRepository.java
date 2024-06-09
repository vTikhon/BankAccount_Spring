package com.bellintegrator.bankaccount.repository.interfaces;

import com.bellintegrator.bankaccount.models.Account;
import com.bellintegrator.bankaccount.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    Optional<Card> findByCardNumber(String cardNumber);
    boolean existsByCardNumber(String cardNumber);

    @Query("SELECT c FROM Card c JOIN c.account a JOIN a.client cl WHERE cl.id = :clientId")
    List<Card> findAllByClientId(@Param("clientId") Long clientId);


    List<Card> findAllByAccountId(Long accountId);
}
