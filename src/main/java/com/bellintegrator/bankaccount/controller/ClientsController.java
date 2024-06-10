package com.bellintegrator.bankaccount.controller;

import com.bellintegrator.bankaccount.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ClientsController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/clients")
    public String showClients(Model model) {
        model.addAttribute("clients", clientService.getAllClients());
        return "clients";
    }
}
