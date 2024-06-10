package com.bellintegrator.bankaccount.controller;

import com.bellintegrator.bankaccount.dto.AccountDTO;
import com.bellintegrator.bankaccount.dto.CardDTO;
import com.bellintegrator.bankaccount.service.AccountService;
import com.bellintegrator.bankaccount.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CardsController {

    @Autowired
    private CardService cardService;
    @Autowired
    private AccountService accountService;

    @GetMapping("/cards")
    public String showCards(Model model) {
        List<CardDTO> cardsDTO = cardService.getAllCards();
        Map<Long, Long> cardAccountMap = new HashMap<>();
        for (CardDTO cardDTO : cardsDTO) {
            AccountDTO accountDTO = accountService.getAccountByCard(cardDTO);
            if (accountDTO != null) {
                cardAccountMap.put(cardDTO.getId(), accountDTO.getId());
            }
        }
        model.addAttribute("cards", cardService.getAllCards());
        model.addAttribute("cardAccountMap", cardAccountMap);
        return "cards";
    }
}
