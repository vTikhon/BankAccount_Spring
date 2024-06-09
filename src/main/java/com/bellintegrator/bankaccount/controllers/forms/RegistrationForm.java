package com.bellintegrator.bankaccount.controllers.forms;

import com.bellintegrator.bankaccount.models.dto.AccountDTO;
import com.bellintegrator.bankaccount.models.dto.CardDTO;
import com.bellintegrator.bankaccount.models.dto.ClientDTO;
import com.bellintegrator.bankaccount.models.dto.PasswordDTO;
import lombok.Data;

@Data
public class RegistrationForm {

    private ClientDTO clientDTO;
    private AccountDTO accountDTO;
    private CardDTO cardDTO;
    private PasswordDTO passwordDTO;

}

