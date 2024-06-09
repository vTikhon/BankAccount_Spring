package com.bellintegrator.bankaccount.models.dto.mapper;

import com.bellintegrator.bankaccount.models.dto.ClientDTO;
import com.bellintegrator.bankaccount.models.Client;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ClientMapper {


    ClientDTO clientToClientDTO(Client client);

    @Mapping(target = "accounts", ignore = true)
    Client clientDTOToClient(ClientDTO clientDTO);

}

