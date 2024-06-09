package com.bellintegrator.bankaccount.models.dto;

import com.bellintegrator.bankaccount.models.enums.AccountType;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class AccountDTO {
    private Long id;
    private String accountNumber;
    private AccountType accountType;
    private BigDecimal accountBalance;
    private Long clientId;
}
