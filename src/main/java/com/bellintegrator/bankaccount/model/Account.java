package com.bellintegrator.bankaccount.model;

import com.bellintegrator.bankaccount.model.enums.AccountType;
import com.bellintegrator.bankaccount.model.listeners.AccountListener;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "cards")
@EqualsAndHashCode(exclude = "cards")
@Table(name = "account")
@EntityListeners(AccountListener.class)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 20, max = 20, message = "Account number must be exactly 20 characters long")
    @Pattern(regexp = "\\d+", message = "Account number must contain only digits")
    @Column(name = "account_number", unique = true, nullable = false, length = 20)
    private String accountNumber;

    @Column(name = "account_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Column(name = "account_balance", nullable = false)
    private BigDecimal accountBalance;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Card> cards;
}
