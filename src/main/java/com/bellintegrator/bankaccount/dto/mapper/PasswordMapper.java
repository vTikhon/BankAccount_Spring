package com.bellintegrator.bankaccount.dto.mapper;

import com.bellintegrator.bankaccount.dto.PasswordDTO;
import com.bellintegrator.bankaccount.model.Password;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PasswordMapper {

    @Mapping(source = "client.id", target = "clientId")
    PasswordDTO passwordToPasswordDTO(Password password);

    @Mapping(source = "clientId", target = "client.id")
    Password passwordDTOToPassword(PasswordDTO passwordDTO);

    List<PasswordDTO> toPasswordDTOList(List<Password> passwords);
    List<Password> toPasswordList(List<PasswordDTO> passwordsDTO);


}

