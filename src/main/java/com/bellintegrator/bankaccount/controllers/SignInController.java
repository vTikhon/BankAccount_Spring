package com.bellintegrator.bankaccount.controllers;

import com.bellintegrator.bankaccount.controllers.forms.SignInForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignInController {

    private static final Logger logger = LoggerFactory.getLogger(SignInController.class);

    @GetMapping("/signin")
    public String showSignInForm(Model model) {
        SignInForm signInForm = new SignInForm();
        model.addAttribute("signInForm", signInForm);
        return "signin";
    }

}


