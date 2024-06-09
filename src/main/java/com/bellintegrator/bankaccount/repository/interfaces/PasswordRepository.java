package com.bellintegrator.bankaccount.repository.interfaces;

import com.bellintegrator.bankaccount.models.Password;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordRepository extends JpaRepository<Password, Long> {

    Password findByClientId(Long clientId);
}

