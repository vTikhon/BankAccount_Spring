package com.bellintegrator.bankaccount.controllers.forms;

import com.bellintegrator.bankaccount.models.dto.ClientDTO;
import com.bellintegrator.bankaccount.models.dto.PasswordDTO;
import lombok.Data;

@Data
public class SignInForm {
    private ClientDTO clientDTO;
    private PasswordDTO passwordDTO;
}
