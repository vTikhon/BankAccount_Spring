package com.bellintegrator.bankaccount.controller;

import com.bellintegrator.bankaccount.dto.AccountDTO;
import com.bellintegrator.bankaccount.dto.ClientDTO;
import com.bellintegrator.bankaccount.service.AccountService;
import com.bellintegrator.bankaccount.service.ClientService;
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
