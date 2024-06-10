package com.bellintegrator.bankaccount.dto;

import lombok.Data;

@Data
public class PasswordDTO {

    private Long id;
    private String clientPassword;
    private Long clientId;

}
