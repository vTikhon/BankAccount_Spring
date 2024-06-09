package com.bellintegrator.bankaccount.controllers;

import com.bellintegrator.bankaccount.models.dto.AccountDTO;
import com.bellintegrator.bankaccount.models.dto.CardDTO;
import com.bellintegrator.bankaccount.models.dto.ClientDTO;
import com.bellintegrator.bankaccount.repository.AccountService;
import com.bellintegrator.bankaccount.repository.CardService;
import com.bellintegrator.bankaccount.repository.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MyAccountController {

    private static final Logger logger = LoggerFactory.getLogger(MyAccountController.class);

    @Autowired
    private ClientService clientService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private CardService cardService;


    @GetMapping("/myaccount")
    public String showMyAccountFromSecurity(@RequestParam String passport, Model model) {
        ClientDTO clientDTO = clientService.getClientByPassport(passport);
        if (clientDTO == null) {
            logger.error("Current client is null");
            return "error";
        }

        List<AccountDTO> accountsDTO = accountService.getAllAccountsByClientId(clientDTO.getId());
        Map<Long, List<CardDTO>> cardsDTOMap = new HashMap<>();

        for (AccountDTO accountDTO : accountsDTO) {
            List<CardDTO> cardsDTO = cardService.getAllCardsByAccountId(accountDTO.getId());
            cardsDTOMap.put(accountDTO.getId(), cardsDTO);
        }

        model.addAttribute("client", clientDTO);
        model.addAttribute("accounts", accountsDTO);
        model.addAttribute("cardsMap", cardsDTOMap);
        return "myaccount";
    }

}

