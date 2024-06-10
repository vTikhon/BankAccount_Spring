package com.bellintegrator.bankaccount.controller;

import com.bellintegrator.bankaccount.dto.CardDTO;
import com.bellintegrator.bankaccount.service.CardService;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
public class RequestController {

    @Autowired
    private CardService cardService;

    private static final Logger logger = LoggerFactory.getLogger(RequestController.class);

    @PostMapping("/requests")
    public ResponseEntity<String> requestCloseCard(@RequestBody Map<String, String> request) {
        String cardNumber = request.get("cardNumber");
        System.out.println(cardNumber);  // здесь выводит текст "${card.cardNumber}"
        logger.info("Received request to close card with number: {}", cardNumber);
        try {
            CardDTO cardDTO = cardService.getCardByNumber(cardNumber);
            if (cardDTO == null) {
                logger.warn("Card with number {} not found", cardNumber);
                throw new EntityNotFoundException();
            }
            cardService.closeCard(cardNumber);
            logger.info("Successfully closed card with number: {}", cardDTO.getCardNumber());
            return ResponseEntity.ok("Request to close card is accepted. Card Number: " + cardDTO.getCardNumber());
        } catch (EntityNotFoundException e) {
            logger.error("Card not found with number: {}", cardNumber, e);
            return ResponseEntity.status(404).body("Card not found");
        } catch (Exception e) {
            logger.error("An error occurred while processing the request for card number: {}", cardNumber, e);
            return ResponseEntity.status(500).body("An error occurred while processing the request");
        }
    }
}

