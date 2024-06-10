package com.bellintegrator.bankaccount.controller.forms;

import com.bellintegrator.bankaccount.dto.ClientDTO;
import com.bellintegrator.bankaccount.dto.PasswordDTO;
import lombok.Data;

@Data
public class SignInForm {
    private ClientDTO clientDTO;
    private PasswordDTO passwordDTO;
}
