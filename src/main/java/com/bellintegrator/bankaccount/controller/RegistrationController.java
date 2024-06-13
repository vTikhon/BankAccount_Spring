package com.bellintegrator.bankaccount.controller;

import com.bellintegrator.bankaccount.controller.forms.RegistrationForm;
import com.bellintegrator.bankaccount.dto.AccountDTO;
import com.bellintegrator.bankaccount.dto.CardDTO;
import com.bellintegrator.bankaccount.dto.ClientDTO;
import com.bellintegrator.bankaccount.dto.PasswordDTO;
import com.bellintegrator.bankaccount.model.enums.AccountType;
import com.bellintegrator.bankaccount.model.enums.CardStatus;
import com.bellintegrator.bankaccount.model.enums.PaymentSystem;
import com.bellintegrator.bankaccount.service.AccountService;
import com.bellintegrator.bankaccount.service.CardService;
import com.bellintegrator.bankaccount.service.ClientService;
import com.bellintegrator.bankaccount.service.PasswordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.math.BigDecimal;

@Controller
public class RegistrationController {

//    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ClientService clientService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private PasswordService passwordService;
    @Autowired
    private CardService cardService;

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        RegistrationForm registrationForm = new RegistrationForm();
        prepareRegistrationForm(model, registrationForm, "");
        return "registration";
    }

    @PostMapping("/registration")
    public String registerClient(@ModelAttribute("registrationForm") RegistrationForm registrationForm, Model model) {
        if (clientService.checkPassportNoExists(registrationForm.getClientDTO().getPassport()) != null) {
            prepareRegistrationForm(model, registrationForm, "Passport already exists.");
            return "registration";
        }
        try {
            creatingNewClient(registrationForm);
        } catch (Exception e) {
            prepareRegistrationForm(model, registrationForm, "An error occurred during registration: " + e.getMessage());
            return "registration";
        }
        return "redirect:/signin";
    }

    private void prepareRegistrationForm(Model model, RegistrationForm registrationForm, String errorMessage) {
        model.addAttribute("error", errorMessage);
        model.addAttribute("registrationForm", registrationForm);
        model.addAttribute("accountTypes", AccountType.values());
        model.addAttribute("paymentSystems", PaymentSystem.values());
    }

    private void creatingNewClient(RegistrationForm registrationForm) {
//        logger.info("Registering new client with passport: {}", registrationForm.getClientDTO().getPassport());
        RegistrationForm newRegistrationForm = new RegistrationForm();
        newRegistrationForm.setClientDTO(registrationForm.getClientDTO());
        newRegistrationForm.setPasswordDTO(registrationForm.getPasswordDTO());
        newRegistrationForm.setAccountDTO(registrationForm.getAccountDTO());
        newRegistrationForm.setCardDTO(registrationForm.getCardDTO());

        // Create Client
        ClientDTO clientDTO = clientService.createNewClient(newRegistrationForm.getClientDTO());
        newRegistrationForm.setClientDTO(clientDTO);

        // Create Password
        newRegistrationForm.getPasswordDTO().setClientId(clientDTO.getId());
        PasswordDTO passwordDTO = newRegistrationForm.getPasswordDTO();
        passwordDTO.setClientPassword(passwordEncoder.encode(passwordDTO.getClientPassword()));
        newRegistrationForm.setPasswordDTO(passwordDTO);
        passwordDTO = passwordService.createPassword(newRegistrationForm.getPasswordDTO());
        newRegistrationForm.setPasswordDTO(passwordDTO);

        // Create Account
        newRegistrationForm.getAccountDTO().setAccountBalance(BigDecimal.ZERO);
        newRegistrationForm.getAccountDTO().setClientId(clientDTO.getId());
        AccountDTO accountDTO = accountService.createAccount(newRegistrationForm.getAccountDTO());
        newRegistrationForm.setAccountDTO(accountDTO);

        // Create Card
        if (accountDTO.getAccountType() == AccountType.DEBIT || accountDTO.getAccountType() == AccountType.CREDIT) {
            newRegistrationForm.getCardDTO().setCardBalance(accountDTO.getAccountBalance());
            newRegistrationForm.getCardDTO().setCardStatus(CardStatus.ACTIVE);
            newRegistrationForm.getCardDTO().setAccountId(accountDTO.getId());
            CardDTO cardDTO = cardService.createCard(newRegistrationForm.getCardDTO());
            newRegistrationForm.setCardDTO(cardDTO);
        }
    }
}
