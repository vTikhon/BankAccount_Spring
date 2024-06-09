package com.bellintegrator.bankaccount.repository.interfaces;

import com.bellintegrator.bankaccount.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByPassport(String passport);

    @Query("SELECT c FROM Client c JOIN c.accounts a WHERE a.id = :accountId")
    Client findByAccountId(@Param("accountId") Long accountId);

}

