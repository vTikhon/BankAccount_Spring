package com.bellintegrator.bankaccount.service;

import com.bellintegrator.bankaccount.model.Password;
import com.bellintegrator.bankaccount.dto.PasswordDTO;
import com.bellintegrator.bankaccount.dto.mapper.PasswordMapper;
import com.bellintegrator.bankaccount.repository.PasswordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class PasswordService {

//    private static final Logger logger = LoggerFactory.getLogger(PasswordService.class);

    private final PasswordMapper passwordMapper;
    private final PasswordRepository passwordRepository;

    public PasswordService(PasswordRepository passwordRepository, PasswordMapper passwordMapper) {
        this.passwordRepository = passwordRepository;
        this.passwordMapper = passwordMapper;
    }

    @Transactional
    public PasswordDTO createPassword(PasswordDTO passwordDTO) {
//        logger.debug("Attempting to create a new password");
        if (passwordDTO == null || passwordDTO.getClientPassword() == null || passwordDTO.getClientPassword().isEmpty()) {
//            logger.error("Password cannot be null or empty");
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        if (passwordDTO.getClientId() == null) {
//            logger.error("Client_id cannot be null or empty");
            throw new IllegalArgumentException("Client_id cannot be null or empty");
        }

        Password password = passwordMapper.passwordDTOToPassword(passwordDTO);
        passwordRepository.save(password);
//        logger.info("Password created with ID: {}", password.getId());
        return passwordMapper.passwordToPasswordDTO(password);
    }

    public PasswordDTO getPasswordByClientId(Long clientId) {
        Password password = passwordRepository.findByClientId(clientId);
        if (password == null) {
            return null;
        }
        return passwordMapper.passwordToPasswordDTO(password);
    }

    public List<PasswordDTO> getAllPasswords() {
        return passwordMapper.toPasswordDTOList(passwordRepository.findAll());
    }
}
