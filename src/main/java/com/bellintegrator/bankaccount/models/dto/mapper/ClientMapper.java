package com.bellintegrator.bankaccount.models.dto.mapper;

import com.bellintegrator.bankaccount.models.dto.ClientDTO;
import com.bellintegrator.bankaccount.models.Client;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ClientMapper {


    ClientDTO clientToClientDTO(Client client);


    Client clientDTOToClient(ClientDTO clientDTO);

}

