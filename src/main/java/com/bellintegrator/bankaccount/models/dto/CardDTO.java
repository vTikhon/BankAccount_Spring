package com.bellintegrator.bankaccount.models.dto;

import com.bellintegrator.bankaccount.models.enums.CardStatus;
import com.bellintegrator.bankaccount.models.enums.PaymentSystem;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class CardDTO {

    private Long id;
    private String cardNumber;
    private BigDecimal cardBalance;
    private PaymentSystem paymentSystem;
    private CardStatus cardStatus;
    private Long accountId;
}
