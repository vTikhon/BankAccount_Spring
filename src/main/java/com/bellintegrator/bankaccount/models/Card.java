package com.bellintegrator.bankaccount.models;

import com.bellintegrator.bankaccount.models.enums.CardStatus;
import com.bellintegrator.bankaccount.models.enums.PaymentSystem;
import com.bellintegrator.bankaccount.models.listeners.CardListener;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "card")
@EntityListeners(CardListener.class)
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 20, max = 20, message = "Card number must be exactly 20 characters long")
    @Pattern(regexp = "\\d+", message = "Card number must contain only digits")
    @Column(name = "card_number", nullable = false, unique = true, length = 20)
    private String cardNumber;

    @Column(name = "card_balance", nullable = false)
    private BigDecimal cardBalance;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_system", nullable = false)
    private PaymentSystem paymentSystem;

    @Enumerated(EnumType.STRING)
    @Column(name = "card_status", nullable = false)
    private CardStatus cardStatus;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;
}
