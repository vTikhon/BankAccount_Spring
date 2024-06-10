package com.bellintegrator.bankaccount.controller.forms;

import com.bellintegrator.bankaccount.dto.AccountDTO;
import com.bellintegrator.bankaccount.dto.CardDTO;
import com.bellintegrator.bankaccount.dto.ClientDTO;
import com.bellintegrator.bankaccount.dto.PasswordDTO;
import lombok.Data;

@Data
public class RegistrationForm {

    private ClientDTO clientDTO;
    private AccountDTO accountDTO;
    private CardDTO cardDTO;
    private PasswordDTO passwordDTO;

}

