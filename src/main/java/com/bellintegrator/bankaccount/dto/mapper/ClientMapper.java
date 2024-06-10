package com.bellintegrator.bankaccount.dto.mapper;

import com.bellintegrator.bankaccount.dto.ClientDTO;
import com.bellintegrator.bankaccount.model.Client;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ClientMapper {


    ClientDTO clientToClientDTO(Client client);

    @Mapping(target = "accounts", ignore = true)
    Client clientDTOToClient(ClientDTO clientDTO);

}

