package com.bellintegrator.bankaccount.controller;

import com.bellintegrator.bankaccount.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PasswordsController {

    @Autowired
    private PasswordService passwordService;

    @GetMapping("/passwords")
    public String showMyAccount(Model model) {
        model.addAttribute("passwords", passwordService.getAllPasswords());
        return "passwords";
    }
}
