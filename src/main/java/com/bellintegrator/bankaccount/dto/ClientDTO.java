package com.bellintegrator.bankaccount.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ClientDTO {
    private Long id;
    private String firstName;
    private String surname;
    private LocalDate dateOfBirth;
    private String passport;

}

