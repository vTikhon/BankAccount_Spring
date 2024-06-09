package com.bellintegrator.bankaccount.controllers;

import com.bellintegrator.bankaccount.models.dto.AccountDTO;
import com.bellintegrator.bankaccount.models.dto.ClientDTO;
import com.bellintegrator.bankaccount.repository.AccountService;
import com.bellintegrator.bankaccount.repository.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AccountsController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private ClientService clientService;

    @GetMapping("/accounts")
    public String showAccounts(Model model) {
        List<AccountDTO> accountsDTO = accountService.getAllAccounts();
        Map<Long, Long> accountClientMap = new HashMap<>();
        for (AccountDTO accountDTO : accountsDTO) {
            ClientDTO clientDTO = clientService.getClientByAccount(accountDTO);
            if (clientDTO != null) {
                accountClientMap.put(accountDTO.getId(), clientDTO.getId());
            }
        }
        model.addAttribute("accounts", accountsDTO);
        model.addAttribute("accountClientMap", accountClientMap);
        return "accounts";
    }
}
