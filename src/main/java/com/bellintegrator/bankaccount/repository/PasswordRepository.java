package com.bellintegrator.bankaccount.repository;

import com.bellintegrator.bankaccount.model.Password;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordRepository extends JpaRepository<Password, Long> {

    Password findByClientId(Long clientId);
}

