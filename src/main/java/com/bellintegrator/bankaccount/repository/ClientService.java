package com.bellintegrator.bankaccount.repository;

import com.bellintegrator.bankaccount.models.Client;
import com.bellintegrator.bankaccount.models.dto.mapper.ClientMapper;
import com.bellintegrator.bankaccount.models.dto.AccountDTO;
import com.bellintegrator.bankaccount.models.dto.ClientDTO;
import com.bellintegrator.bankaccount.repository.interfaces.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {

    private static final Logger logger = LoggerFactory.getLogger(ClientService.class);

    private final ClientMapper clientMapper;
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    @Transactional(readOnly = true)
    public List<ClientDTO> getAllClients() {
        logger.info("Fetching all clients");
        List<Client> clients = clientRepository.findAll();
        List<ClientDTO> clientDTOs = new ArrayList<>();
        for (Client client : clients) {
            clientDTOs.add(clientMapper.clientToClientDTO(client));
        }
        return clientDTOs;
    }

    @Transactional
    public ClientDTO createNewClient(ClientDTO clientDTO) {
        validateClient(clientDTO);
        Client client = clientMapper.clientDTOToClient(clientDTO);
        clientRepository.save(client);
        logger.info("Client created with ID: {}", client.getId());
        return clientMapper.clientToClientDTO(client);
    }

    @Transactional(readOnly = true)
    public ClientDTO getClientByPassport(String passport) {
        logger.info("Fetching client with passport: {}", passport);
        Client client = clientRepository.findByPassport(passport)
                .orElseThrow(() -> {
                    String errorMessage = "Client with passport " + passport + " not found";
                    logger.error(errorMessage);
                    return new EntityNotFoundException(errorMessage);
                });
        return clientMapper.clientToClientDTO(client);
    }

    @Transactional(readOnly = true)
    public ClientDTO checkPassportNoExists(String passport) {
        logger.info("Checking if passport exists: {}", passport);
        Client client = clientRepository.findByPassport(passport)
                .orElse(null);
        if (client != null) {
            return clientMapper.clientToClientDTO(client);
        }
        return null;
    }

    @Transactional(readOnly = true)
    public ClientDTO getClientByAccount(AccountDTO accountDTO) {
        Client client = clientRepository.findByAccountId(accountDTO.getId());
        if (client != null) {
            return clientMapper.clientToClientDTO(client);
        }
        return null;
    }

    private void validateClient(ClientDTO clientDTO) {
        if (clientDTO.getFirstName() == null || clientDTO.getFirstName().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be null or empty");
        }
        if (clientDTO.getSurname() == null || clientDTO.getSurname().isEmpty()) {
            throw new IllegalArgumentException("Surname cannot be null or empty");
        }
        if (clientDTO.getPassport() == null || clientDTO.getPassport().isEmpty()) {
            throw new IllegalArgumentException("Passport cannot be null or empty");
        }
        if (clientDTO.getDateOfBirth() == null ) {
            throw new IllegalArgumentException("DateOfBirth cannot be null");
        }
    }

}
